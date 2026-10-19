-- =========================================================================
-- UPSC MASTERKEY — COMPLETE SUPABASE / POSTGRESQL BACKEND SCHEMA & SEED DATA
-- Run this script in your Supabase SQL Editor (https://supabase.com/dashboard)
-- to instantly configure your cloud database, RLS policies, and sample data.
-- =========================================================================

-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 1. SYLLABUS TABLE
CREATE TABLE IF NOT EXISTS syllabus (
    id VARCHAR(60) PRIMARY KEY, -- e.g. "PRELIMS_GS1_POLITY_CONSTITUTION"
    stage VARCHAR(10) NOT NULL CHECK (stage IN ('PRELIMS', 'MAINS')),
    paper VARCHAR(20) NOT NULL, -- 'GS1', 'GS2', 'GS3', 'GS4', 'CSAT', 'ESSAY'
    subject VARCHAR(100) NOT NULL, -- 'Indian Polity & Governance'
    topic_title VARCHAR(250) NOT NULL,
    subtopics TEXT[] DEFAULT '{}',
    sort_order INT DEFAULT 0,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. PREVIOUS YEAR QUESTIONS (PYQs) TABLE
CREATE TABLE IF NOT EXISTS pyqs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    exam_stage VARCHAR(10) NOT NULL CHECK (exam_stage IN ('PRELIMS', 'MAINS')),
    exam_year INT NOT NULL, -- e.g. 2024
    paper VARCHAR(20) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    syllabus_id VARCHAR(60) REFERENCES syllabus(id) ON DELETE SET NULL,
    question_text TEXT NOT NULL,
    options JSONB, -- For Prelims: {"A": "...", "B": "...", "C": "...", "D": "..."}
    correct_option CHAR(1), -- 'A', 'B', 'C', or 'D' (NULL for Mains)
    short_explanation TEXT NOT NULL, -- 3-bullet core takeaway
    model_dimensions TEXT[] DEFAULT '{}', -- For Mains: ["Intro", "Body Arguments", "Way Forward"]
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 3. HIGH-YIELD REVISION NOTES TABLE
CREATE TABLE IF NOT EXISTS notes (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    paper VARCHAR(20) NOT NULL, -- 'GS1', 'GS2', 'GS3', 'GS4'
    subject VARCHAR(100) NOT NULL,
    syllabus_id VARCHAR(60) REFERENCES syllabus(id) ON DELETE SET NULL,
    title VARCHAR(250) NOT NULL,
    summary_excerpt TEXT NOT NULL,
    definition_context TEXT NOT NULL,
    constitutional_articles TEXT[] DEFAULT '{}',
    key_dimensions TEXT[] NOT NULL,
    way_forward TEXT NOT NULL,
    read_time_minutes INT DEFAULT 3,
    is_published BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 4. CURRENT AFFAIRS UPDATES TABLE
CREATE TABLE IF NOT EXISTS current_affairs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(300) NOT NULL,
    source_name VARCHAR(100) NOT NULL, -- 'The Hindu', 'PIB', 'Indian Express'
    published_date DATE NOT NULL DEFAULT CURRENT_DATE,
    syllabus_paper VARCHAR(20) NOT NULL, -- 'GS-2', 'GS-3'
    syllabus_topic VARCHAR(150) NOT NULL, -- 'Federalism & Governor Assent'
    summary_bullets TEXT[] NOT NULL,
    prelims_facts TEXT[] DEFAULT '{}',
    mains_takeaway TEXT,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- Indexes for lightning-fast queries
CREATE INDEX IF NOT EXISTS idx_syllabus_stage_paper ON syllabus(stage, paper, sort_order);
CREATE INDEX IF NOT EXISTS idx_pyqs_stage_subject_year ON pyqs(exam_stage, subject, exam_year DESC);
CREATE INDEX IF NOT EXISTS idx_notes_paper ON notes(paper, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_current_affairs_date ON current_affairs(published_date DESC);

-- Enable Row Level Security (RLS)
ALTER TABLE syllabus ENABLE ROW LEVEL SECURITY;
ALTER TABLE pyqs ENABLE ROW LEVEL SECURITY;
ALTER TABLE notes ENABLE ROW LEVEL SECURITY;
ALTER TABLE current_affairs ENABLE ROW LEVEL SECURITY;

-- Allow Public Read-Only Access for all mobile aspirants
CREATE POLICY "Public Read Syllabus" ON syllabus FOR SELECT USING (true);
CREATE POLICY "Public Read PYQs" ON pyqs FOR SELECT USING (true);
CREATE POLICY "Public Read Notes" ON notes FOR SELECT USING (true);
CREATE POLICY "Public Read Current Affairs" ON current_affairs FOR SELECT USING (true);

-- =========================================================================
-- SEED DATA: ACTUAL UPSC CSE SYLLABUS, PYQS, NOTES & CURRENT AFFAIRS
-- =========================================================================

-- Syllabus
INSERT INTO syllabus (id, stage, paper, subject, topic_title, subtopics, sort_order) VALUES
('PRE_POLITY_CONST', 'PRELIMS', 'GS1', 'Polity & Governance', 'Indian Constitution & Political System', ARRAY['Historical Underpinnings', 'Preamble & Basic Structure', 'Fundamental Rights & Duties', 'Directive Principles of State Policy'], 1),
('PRE_POLITY_PARL', 'PRELIMS', 'GS1', 'Polity & Governance', 'Union Executive & Parliament', ARRAY['President, Governor & Ordinances', 'Parliamentary Committees & Sessions', 'Money Bills & Legislative Process'], 2),
('PRE_ENV_BIODIV', 'PRELIMS', 'GS1', 'Environment & Ecology', 'Biodiversity, Ecosystems & Conservation', ARRAY['Protected Areas & National Parks', 'IUCN Red List Categories', 'Ramsar Wetlands & CITES'], 3),
('PRE_HIST_MODERN', 'PRELIMS', 'GS1', 'Modern Indian History', 'Freedom Struggle & National Movement', ARRAY['Early Revolts & 1857 Mutiny', 'INC Sessions & Moderates/Extremists', 'Gandhian Mass Movements', 'Constitutional Acts 1909-1935'], 4),
('MAINS_GS2_FED', 'MAINS', 'GS2', 'Polity & Governance', 'Federal Structure & Inter-State Relations', ARRAY['Inter-State Councils & GST Council', 'Governor Discretionary Powers', 'Finance Commission Devolutions', 'Fifth & Sixth Schedule Areas'], 1),
('MAINS_GS3_ECO', 'MAINS', 'GS3', 'Economy & Development', 'Inclusive Growth & Macroeconomic Stability', ARRAY['Fiscal Policy & Deficit Targets', 'Monetary Policy Framework', 'Inflation Targeting & RBI Operations'], 2)
ON CONFLICT (id) DO NOTHING;

-- PYQs
INSERT INTO pyqs (exam_stage, exam_year, paper, subject, question_text, options, correct_option, short_explanation, model_dimensions) VALUES
('PRELIMS', 2024, 'GS1', 'Polity & Governance', 'Consider the following statements regarding the Inter-State Council:\n1. It is a permanent constitutional body established under Article 263.\n2. The Prime Minister is the Chairman of the Council.\n3. Its recommendations are legally binding on all states.\nWhich of the statements given above is/are correct?',
 '{"A": "1 and 2 only", "B": "2 only", "C": "1 and 3 only", "D": "1, 2 and 3"}'::jsonb,
 'B',
 '• Article 263 empowers the President to establish the Council, but it is NOT permanent (convened by presidential decree).\n• The Prime Minister is Chairman with Chief Ministers as members.\n• Recommendations are advisory and consultative, NOT legally binding.',
 ARRAY[]::text[]),
('PRELIMS', 2023, 'GS1', 'Environment & Ecology', 'Consider the following protected areas:\n1. Bandipur\n2. Bhitarkanika\n3. Manas\n4. Sunderbans\nWhich of the above are declared Tiger Reserves in India?',
 '{"A": "1 and 2 only", "B": "1, 3 and 4 only", "C": "2, 3 and 4 only", "D": "1, 2, 3 and 4"}'::jsonb,
 'B',
 '• Bandipur (Karnataka), Manas (Assam), and Sunderbans (West Bengal) are designated Project Tiger Reserves.\n• Bhitarkanika (Odisha) is a National Park and Ramsar Wetland known for Saltwater Crocodiles, but not a Tiger Reserve.',
 ARRAY[]::text[]),
('MAINS', 2024, 'GS2', 'Polity & Governance', '“The institution of the Governor in India has often been a focal point of federal friction between the Union and the States.” Critically examine with reference to recent judicial pronouncements.',
 NULL,
 NULL,
 'UPSC CSE 2024 GS-2 Question on Article 200 & 201',
 ARRAY[
    'Introduction: Constitutional mandate of the Governor as dual entity (constitutional head + Union representative) under Article 153-163.',
    'Dimensions of Friction: Indefinite withholding of bill assent (Article 200), delay in convening assembly sessions, and invoking discretionary powers.',
    'Judicial Precedents: Shamsher Singh (1974), Rameshwar Prasad (2006), and recent SC rulings mandating that Governors cannot exercise pocket veto on state bills.',
    'Way Forward: Implement Sarkaria Commission recommendations (6-month limit on assent) and Punchhi Commission guidelines.'
 ])
ON CONFLICT DO NOTHING;

-- Notes
INSERT INTO notes (paper, subject, title, summary_excerpt, definition_context, constitutional_articles, key_dimensions, way_forward, read_time_minutes) VALUES
('GS2', 'Polity & Governance', 'Governor Assent to State Bills & Article 200', 
 'Comprehensive high-yield notes on gubernatorial assent, constitutional limitations, and landmark Supreme Court verdicts.',
 'Article 200 outlines the four options available to a Governor when a bill passed by the state legislature is presented for assent: (1) Assent, (2) Withhold assent, (3) Reserve for the President, or (4) Return for reconsideration.',
 ARRAY['Article 200 (Assent)', 'Article 201 (Bills reserved for President)', 'Article 163 (Discretionary Powers)'],
 ARRAY[
    'Constitutional Ambiguity: Absence of an explicit timeline under "as soon as possible" in Article 200 leading to legislative deadlocks.',
    'Federal Principle: Governors are non-elected constitutional functionaries bound by the aid and advice of the Council of Ministers (Article 163(1)).',
    'Separation of Powers: Indefinite stalling of bills undermines representative democracy and legislative primacy.'
 ],
 'Adopt the Sarkaria Commission guideline mandating a 6-month resolution period, and formalize the Supreme Court dictum that return of a reconsidered bill leaves the Governor no option but to grant assent.',
 3),
('GS3', 'Economy & Development', 'Monetary Policy Framework & Section 45ZB',
 'Statutory structure of the MPC, inflation targeting band, and macroeconomic transmission mechanisms.',
 'The Monetary Policy Committee (MPC) is a 6-member statutory committee formed under Section 45ZB of the amended RBI Act, 1934, tasked with fixing the benchmark repo rate.',
 ARRAY['Section 45ZB (RBI Act, 1934)', 'Section 45ZA (Flexible Inflation Target)'],
 ARRAY[
    'Composition: 3 RBI officials (Governor as ex-officio Chairperson) + 3 external government-appointed experts.',
    'Target: Consumer Price Index (CPI) headline inflation of 4% with a tolerance band of +/- 2% (2% to 6%).',
    'Accountability Clause: If inflation remains outside the band for 3 consecutive quarters, RBI must submit a written failure report to the central government.'
 ],
 'Complement interest rate adjustments with proactive fiscal supply-side management (buffer stocks, export duty rationalization) to tame volatile food and fuel shocks.',
 4)
ON CONFLICT DO NOTHING;

-- Current Affairs
INSERT INTO current_affairs (title, source_name, published_date, syllabus_paper, syllabus_topic, summary_bullets, prelims_facts, mains_takeaway) VALUES
('Supreme Court Fixes Principle on Governor Assent to State Bills', 'The Hindu', CURRENT_DATE, 'GS-2', 'Executive & Judiciary / Article 200',
 ARRAY[
    'A 3-judge Constitution Bench held that Governors cannot sit indefinitely on bills passed by the State Legislature.',
    'If a Governor chooses to withhold assent, the bill must be returned to the House immediately with a message.',
    'Once the legislative assembly passes the bill again (with or without amendment), the Governor has no discretion and must grant assent.'
 ],
 ARRAY[
    'Article 200 phrase "as soon as possible" interpreted as a constitutional imperative requiring swift action.',
    'Shamsher Singh v. State of Punjab (1974) re-affirmed: Governor does not possess discretionary pocket veto.'
 ],
 'Strengthens cooperative federalism by ensuring state legislative authority is not stalled by non-elected constitutional appointees.'),
('Monetary Policy Committee Unanimously Retains Repo Rate at 6.5%', 'Indian Express', CURRENT_DATE, 'GS-3', 'Indian Economy & Macroeconomics',
 ARRAY[
    'The RBI MPC voted to keep the benchmark policy repo rate unchanged at 6.50% to anchor inflation expectations.',
    'Projected real GDP growth for India maintained at 7.2% for the current fiscal year.',
    'CPI headline inflation projected at 4.5% amidst persistent geopolitical and weather-induced food inflation pressures.'
 ],
 ARRAY[
    'Statutory committee formed under Section 45ZB of the RBI Act, 1934.',
    'Quorum for the meeting is 4 members; Governor has casting vote in case of a tie.'
 ],
 'Highlights the delicate trade-off between private investment revival and consumer price stability in emerging economies.')
ON CONFLICT DO NOTHING;
