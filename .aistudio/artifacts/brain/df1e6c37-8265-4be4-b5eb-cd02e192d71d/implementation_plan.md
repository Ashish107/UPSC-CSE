# UPSC Pathfinder: Product & Technical Architecture Specification

An intelligent, distraction-free preparation companion for UPSC Civil Services Examination (CSE) aspirants that unifies adaptive daily testing, syllabus-linked current affairs, 2013–present PYQ concept mapping, AI-evaluated Mains answer writing, and dynamic schedule balancing.

---

### User Review & Critical Decisions

> [!IMPORTANT]
> The product specification below has been synthesized around your confirmed preferences and UPSC CSE pedagogy:
> - **Primary Deliverable Focus**: Comprehensive product & technical specification blueprint designed for immediate engineering handoff across mobile and cloud services.
> - **Primary Target Audience**: Balanced Prelims + Mains aspirants balancing static GS-1 to GS-4, CSAT, Optional subjects, and daily current affairs.
> - **Visual Identity**: Deep Navy (`#0A192F` / `#0F172A`) paired with Warm Saffron (`#F59E0B` / `#D97706`) and parchment accents, featuring an editorial, distraction-free typography hierarchy in both dark and light modes.
> - **AI Integration Boundary**: Multi-tiered LLM orchestration (Fast inference for daily note summarization & quiz feedback; structured reasoning models for Mains answer evaluation with strict rubric rubrics).

---

## 1. Overview & Core Concept

### 1.1 Product Vision & Positioning Statement
- **Vision**: To eliminate the fragmented, overwhelming chaos of UPSC CSE preparation by unifying static syllabus mastery, dynamic current affairs, real-time previous year question (PYQ) concept deconstruction, and personalized adaptive study pacing into a single disciplined ecosystem.
- **Positioning Statement**: For serious civil services aspirants overwhelmed by endless PDF compilations, disparate test series, and lack of actionable Mains feedback, **UPSC Pathfinder** is the only AI-guided mentor platform that dynamically connects every news article and daily quiz directly to the UPSC syllabus and past exam trends, guaranteeing targeted, retention-optimized daily progress.

### 1.2 Target Personas & Journey Maps

```
┌─────────────────────────────────────────────────────────────────────────────────────────┐
│                                ASPIRANT ARCHETYPES                                      │
├───────────────────────────────┬───────────────────────────────┬─────────────────────────┤
│ Persona A: "The Balancer"     │ Persona B: "The Working Pro"  │ Persona C: "The Mains   │
│ (Full-Time Aspirant)          │ (Time-Constrained Aspirant)   │ Veteran" (Repeat Attempt)│
├───────────────────────────────┼───────────────────────────────┼─────────────────────────┤
│ • 8–10 hours available/day    │ • 3–4 hours available/day     │ • Strong prelims base   │
│ • Struggling with backlog &   │ • Needs high-yield micro-     │ • Needs Mains answer    │
│   connecting current to static│   learning and audio digests  │   scoring & trend PYQs  │
│ • Target: Rank < 100 in 2027  │ • Weekend mock surge          │ • Optional subject focus│
└───────────────────────────────┴───────────────────────────────┴─────────────────────────┘
```

#### User Journey: Daily High-Yield Routine (The "Pathfinder Daily Loop")
1. **07:00 AM – Awakening Briefing**: Push notification with daily 5-minute Current Affairs Audio Digest + 5 top syllabus-tagged editorial cards (The Hindu / Indian Express / PIB).
2. **08:30 AM – Daily Diagnostic Quiz**: 15 adaptive Prelims MCQs (10 Static GS + 3 Current Affairs + 2 CSAT). Immediate concept deconstruction with linked PYQs from 2013–2025.
3. **02:00 PM – Structured Block Execution**: Pathfinder timetable prompts the scheduled static block (e.g., *GS-2: Federalism & Inter-State Council*). Integrated Smart Notes provide instant summarization and mind-map triggers.
4. **06:00 PM – Daily Mains Challenge**: 1 GS question displayed with official UPSC timer (7 or 11 minutes). Aspirant uploads handwritten answer photo or types response. AI evaluates against standard UPSC rubric within 60 seconds.
5. **10:00 PM – Spaced Repetition Flashcards & Dynamic Recalibration**: 15 Anki-style review cards. Pathfinder re-balances tomorrow's schedule based on weak quiz topics.

---

## 2. User Experience & Visual Design

### 2.1 Aesthetic Direction & Theme Tokens
- **Vibe**: Scholarly, dignified, distraction-free. Avoids juvenile gamification while maintaining high motivation via subtle streak flames, mastery heatmaps, and clean editorial borders.
- **Color Palette**:
  - `Primary Navy`: `#0B192C` (Deep academic navy for primary surfaces, headers, and focus chrome)
  - `Secondary Slate`: `#1E3E62` (Structural cards, elevated modules, and tab bars)
  - `Warm Saffron Accent`: `#F59E0B` / `#D97706` (Milestones, primary action buttons, streak indicators)
  - `Parchment Light`: `#F8F9FA` / `#F3F4F6` (Reading canvas in light mode)
  - `Dark Background`: `#080D1A` (OLED-friendly deep night reading environment)
  - `Semantic Success`: `#10B981` (Correct answers, syllabus milestone completion)
  - `Semantic Alert / Re-plan`: `#EF4444` (Backlog warnings, weak subject alerts)

### 2.2 Key Screen Specifications

#### 1. Home Dashboard ("War Room")
- **Top Bar**: User avatar, current streak count (e.g., "🔥 42 Days"), and Today's Target Hours tracker (`5.5 / 8 hrs`).
- **Target Countdown**: "UPSC Prelims 2027: 218 Days Left".
- **Today's Command Center**:
  - *Daily Quiz Card*: "Day 142 Prelims Booster (Polity + Sci-Tech + CSAT)" with "Start Quiz (15 Mins)" CTA.
  - *Current Affairs Capsule*: 3 swipeable cards highlighting today's PIB and Editorial developments with syllabus badge tags (`[GS-3: Economy]`).
  - *Mains Daily Practice*: "Today's Question: Evaluate the efficacy of the Finance Commission in mitigating regional fiscal imbalances."
- **Syllabus Progress Ring**: Circular breakdown showing GS-1 (78%), GS-2 (62%), GS-3 (45%), GS-4 (88%), Optional (50%).

#### 2. Daily Adaptive Quiz Experience
- **Header**: Question counter (`Question 4 of 15`), countdown timer, and subject tag (`Polity - Preamble`).
- **Interactive Body**: Clean typography, crisp selection tiles (A, B, C, D).
- **Post-Submission Instant Modal**:
  - *Correct / Incorrect Badge* with immediate marks (+2.00 / -0.66).
  - *Core Concept Card*: 3-bullet core takeaway.
  - *Why UPSC Asked This*: Context tag (e.g., "Relevance: 75th Anniversary of Constitution Adoption / 2020 Prelims Q12 connection").
  - *Related PYQs*: Expandable list of 2017 & 2021 related questions.

#### 3. Current Affairs & Smart Notes Hub
- **Filter Tabs**: `All Sources`, `The Hindu`, `Indian Express`, `PIB`, `Down To Earth`, `Yojana`.
- **Syllabus Matrix Selector**: Filter specifically by GS-1, GS-2, GS-3, GS-4, or Essay topics.
- **Article Reader**:
  - Left margin: Original news extract.
  - Right margin / Bottom sheet: **Smart Note Generator** with toggle buttons:
    - *Prelims Pointers* (Factual, Constitutional articles, committees, species, locations).
    - *Mains Dimension Matrix* (Arguments For, Arguments Against, Way Forward, Supreme Court Judgments).
  - Action: "Save to My GS-2 Revision Deck" or "Export PDF".

#### 4. Personalized Study Schedule (Dynamic Timetable)
- **Weekly Horizon View**: Interactive horizontal calendar showing daily target hours vs. logged hours.
- **Auto-Balancing Trigger**: When an aspirant logs fewer hours than planned, Pathfinder prompts:
  `"You have a 4-hour backlog in GS-3 Agriculture. Redistribute across Wednesday & Thursday evenings?"` with 1-click rebalance.
- **Integrated Pomodoro Focus Timer**: Full-screen distraction-free timer with ambient focus soundscapes.

#### 5. PYQ Concept Explorer (2013–Present)
- **Dual Mode**: `Prelims Explorer` & `Mains Deconstruct`.
- **Search & Filter Grid**: Search by keyword ("Governor discretionary powers"), Year (2013-2025), Paper, or Difficulty.
- **Mains Answer Key & Model Breakdown**:
  - *Question Analysis*: Break down into Directives ("Critically Examine" vs "Discuss").
  - *Structure Blueprint*: Introduction (15%), Body Dimensions (70%), Conclusion (15%).
  - *Keywords & Value-Adds*: Diagram placeholder, relevant Constitutional Articles, Committee recommendations.

---

## 3. Product Strategy, Scope & Monetization

### 3.1 Feature Breakdown: MVP vs Phase 2 vs Phase 3

| Module | MVP (Month 1–3) | Phase 2 (Month 4–6) | Phase 3 (Month 7–9) |
|---|---|---|---|
| **Daily Quizzes** | 15 daily MCQs, instant explanations, basic topic tags, daily streak | Adaptive difficulty engine, negative marking simulator, national percentile leaderboard | Voice-assisted CSAT practice, custom test generation |
| **Current Affairs** | Daily editorial summaries, syllabus tagging, 5 daily CA MCQs | "Make Notes" AI tool, monthly PDF compilation generator, audio podcasts | Automatic mind-map rendering, multi-article synthesis |
| **Study Schedule** | Static intake questionnaire, daily timetable generator, manual task check | Dynamic backlog re-balancing, calendar sync (Google/Apple), study hour analytics | Predictive exam readiness score, mentor override capability |
| **PYQ Explorer** | 2013–2025 Prelims PYQs with explanations, categorized by subject | 2013–2025 Mains PYQs + model answers + "Why Asked" trend analysis | Multi-year comparative trends & heatmaps |
| **Mains Answer AI** | Structured text input, rubric-based feedback on structure & keywords | Handwritten answer OCR upload, diagram detection, score projection | Peer review arena, 1-on-1 human evaluator escalation |
| **Community** | Read-only curated doubt threads | Peer discussion forums, study streak circles | Live mentor AMAs, peer answer-swapping |
| **Language Support**| English | Hindi interface & content | Regional languages (Tamil, Telugu, Marathi) |

### 3.2 Monetization Strategy (Freemium Model)

```
┌───────────────────────────────────────┬───────────────────────────────────────┐
│        FREE TIER ("Aspirant")         │       PATHFINDER PRO (₹999/Month)     │
├───────────────────────────────────────┼───────────────────────────────────────┤
│ • Daily 10-Question Prelims Quiz      │ • Unlimited Adaptive Quizzes & CSAT   │
│ • Daily Current Affairs Headlines     │ • Full "Make Notes" AI Generator      │
│ • 2013–2018 Prelims PYQs              │ • Complete 2013–2025 Prelims & Mains  │
│ • Basic Fixed Study Schedule          │ • Dynamic AI Backlog Re-Balancing     │
│ • 1 AI Mains Answer Evaluation/Week   │ • Daily AI Mains Evaluations (3/day)  │
│ • Community Doubt Reading             │ • Offline Downloads for All Decks     │
└───────────────────────────────────────┴───────────────────────────────────────┘
```

---

## 4. Technical Architecture & Data Strategy

### 4.1 High-Level System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              CLIENT APPLICATIONS                            │
│  ┌───────────────────────────────┐         ┌─────────────────────────────┐  │
│  │ Mobile App (React Native /   │         │ Web App (Next.js 15 App     │  │
│  │ Android Native / iOS)         │         │ Router, Tailwind M3)        │  │
│  └───────────────┬───────────────┘         └──────────────┬──────────────┘  │
└──────────────────┼────────────────────────────────────────┼─────────────────┘
                   ▼                                        ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                          API GATEWAY / LOAD BALANCER                        │
│               (Cloudflare + Envoy / Reverse Proxy + Rate Limiter)           │
└──────────────────────────────────────┬──────────────────────────────────────┘
                                       ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                         BACKEND APPLICATION SERVICES                         │
│  ┌───────────────────────────┐  ┌────────────────────────────────────────┐  │
│  │ Core API Service (NestJS) │  │ AI & Evaluation Service (FastAPI/Py)   │  │
│  │ • User Authentication     │  │ • Mains Answer Writing OCR & Evaluator │  │
│  │ • Schedule Engine         │  │ • Adaptive Quiz Question Generator    │  │
│  │ • Quiz & Leaderboard      │  │ • Article-to-Note Summarizer           │  │
│  │ • Spaced Repetition (SRS) │  │ • Embedding & Vector Retrieval         │  │
│  └─────────────┬─────────────┘  └───────────────────┬────────────────────┘  │
└────────────────┼────────────────────────────────────┼───────────────────────┘
                 ▼                                    ▼
┌──────────────────────────────────┐ ┌────────────────────────────────────────┐
│     STORAGE & CACHE LAYER        │ │           AI & SEARCH SERVICES         │
│  ┌─────────────────────────────┐ │ │  ┌──────────────────────────────────┐  │
│  │ PostgreSQL 16 (Primary DB)  │ │ │  │ Gemini 2.5 Flash (Fast Tasks)    │  │
│  │ • Users, Plans, Syllabus    │ │ │  ├──────────────────────────────────┤  │
│  │ • PYQ Bank, Submissions     │ │ │  │ Claude 3.5 Sonnet / Gemini Pro   │  │
│  ├─────────────────────────────┤ │ │  │ (Mains Answer Deep Evaluation)   │  │
│  │ Redis 7.4 (Cache & Queue)   │ │ │  ├──────────────────────────────────┤  │
│  │ • Active sessions & streaks │ │ │  │ Qdrant / pgvector (Vector Store) │  │
│  │ • BullMQ background workers │ │ │  │ • Semantic search across PYQs    │  │
│  └─────────────────────────────┘ │ │  └──────────────────────────────────┘  │
└──────────────────────────────────┘ └────────────────────────────────────────┘
```

### 4.2 AI Evaluation Pipeline (Mains Answer Deconstruction)

```
[Aspirant Uploads Photo/Text]
         │
         ▼
[Step 1: Document Pre-processing]
 ├── If Image: Google Cloud Vision OCR / Multimodal Vision LLM
 └── Word Count & Legibility Verification
         │
         ▼
[Step 2: Syllabus & Question Context Injection]
 ├── Fetch Question Directive ("Discuss", "Examine", "Critically Analyze")
 ├── Fetch Official Marks (10M / 15M) and Word Limit (150W / 250W)
 └── Retrieve Golden Keyword Matrix & Model Key Dimensions via Vector DB
         │
         ▼
[Step 3: Multi-Criteria Rubric Scoring via LLM]
 ├── 1. Directive & Context Compliance (Weight: 25%)
 ├── 2. Multidimensional Structure: Intro, Body, Conclusion (Weight: 25%)
 ├── 3. Factual & Value-Add Content: Articles, Committees, Diagrams (Weight: 30%)
 └── 4. Language & Coherence (Weight: 20%)
         │
         ▼
[Step 4: Output Synthesis]
 └── Numerical Score (e.g. 5.5/10), Strengths, Missing Dimensions, Model Rewrite
```

### 4.3 Database Schema Outline (PostgreSQL DDL)

```sql
-- 1. Users & Target Profiles
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    full_name VARCHAR(150) NOT NULL,
    target_year INT NOT NULL, -- e.g., 2026, 2027
    optional_subject VARCHAR(100) NOT NULL,
    available_daily_hours NUMERIC(3,1) NOT NULL DEFAULT 6.0,
    tier VARCHAR(20) NOT NULL DEFAULT 'FREE', -- 'FREE', 'PRO'
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- 2. Syllabus Taxonomy
CREATE TABLE syllabus_nodes (
    id VARCHAR(50) PRIMARY KEY, -- e.g. "GS2_POLITY_FEDERALISM"
    paper VARCHAR(10) NOT NULL, -- "GS1", "GS2", "GS3", "GS4", "CSAT", "OPTIONAL"
    subject VARCHAR(100) NOT NULL,
    topic_name VARCHAR(200) NOT NULL,
    parent_id VARCHAR(50) REFERENCES syllabus_nodes(id),
    weightage_index NUMERIC(3,2) DEFAULT 1.00
);

-- 3. Previous Year Questions & Question Bank
CREATE TABLE questions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(15) NOT NULL, -- 'PRELIMS_MCQ', 'MAINS_DESCRIPTIVE'
    paper VARCHAR(10) NOT NULL,
    year INT, -- NULL if proprietary mock question
    syllabus_node_id VARCHAR(50) REFERENCES syllabus_nodes(id),
    question_text TEXT NOT NULL,
    options_json JSONB, -- For MCQs: {"A": "...", "B": "...", "C": "...", "D": "..."}
    correct_option CHAR(1), -- 'A', 'B', 'C', 'D'
    explanation TEXT NOT NULL,
    why_asked_context TEXT,
    model_answer TEXT, -- For Mains
    difficulty_level VARCHAR(15) DEFAULT 'MEDIUM' -- 'EASY', 'MEDIUM', 'HARD'
);

-- 4. User Quiz Attempts
CREATE TABLE quiz_attempts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    question_id UUID REFERENCES questions(id),
    selected_option CHAR(1),
    is_correct BOOLEAN NOT NULL,
    time_spent_seconds INT NOT NULL,
    attempted_at TIMESTAMPTZ DEFAULT NOW()
);

-- 5. Mains Submissions & AI Evaluations
CREATE TABLE mains_submissions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    question_id UUID REFERENCES questions(id),
    submission_mode VARCHAR(10) NOT NULL, -- 'TEXT', 'IMAGE_OCR'
    raw_answer_text TEXT NOT NULL,
    score NUMERIC(3,1), -- e.g. 6.5 out of 10
    evaluation_json JSONB, -- Breakdown of dimensions, keywords, comments
    status VARCHAR(20) DEFAULT 'COMPLETED', -- 'PENDING', 'PROCESSING', 'COMPLETED'
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 6. Current Affairs Articles
CREATE TABLE current_affairs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(300) NOT NULL,
    source VARCHAR(100) NOT NULL, -- 'THE_HINDU', 'PIB', 'INDIAN_EXPRESS'
    published_date DATE NOT NULL,
    summary TEXT NOT NULL,
    prelims_pointers JSONB NOT NULL, -- ["Article 356 invoked...", "Key species: Great Indian Bustard"]
    mains_dimensions JSONB NOT NULL, -- {"arguments_for": [], "arguments_against": []}
    syllabus_node_id VARCHAR(50) REFERENCES syllabus_nodes(id),
    created_at TIMESTAMPTZ DEFAULT NOW()
);

-- 7. Adaptive Study Timetable
CREATE TABLE study_tasks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    syllabus_node_id VARCHAR(50) REFERENCES syllabus_nodes(id),
    scheduled_date DATE NOT NULL,
    allocated_minutes INT NOT NULL,
    completed_minutes INT DEFAULT 0,
    is_completed BOOLEAN DEFAULT FALSE,
    task_type VARCHAR(20) NOT NULL -- 'READING', 'QUIZ', 'MAINS_WRITING', 'REVISION'
);

-- Performance Indexes
CREATE INDEX idx_questions_syllabus ON questions(syllabus_node_id);
CREATE INDEX idx_quiz_attempts_user ON quiz_attempts(user_id, attempted_at DESC);
CREATE INDEX idx_study_tasks_schedule ON study_tasks(user_id, scheduled_date);
CREATE INDEX idx_current_affairs_date ON current_affairs(published_date DESC);
```

---

## 5. 90-Day Development Roadmap

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           90-DAY EXECUTION TIMELINE                         │
├─────────────────────┬─────────────────────┬─────────────────────────────────┤
│ Month 1 (Days 1–30) │ Month 2 (Days 31–60)│ Month 3 (Days 61–90)            │
│ Foundational Core   │ Intelligence Engine │ Polish, Scale & Launch          │
├─────────────────────┼─────────────────────┼─────────────────────────────────┤
│ • DDL & Postgres DB │ • AI Mains Review   │ • Offline SQLite sync & cache   │
│ • Syllabus ontology │   engine (FastAPI)  │ • Mock Test series module       │
│ • Daily Quiz engine │ • "Make Notes" AI   │ • Payment Gateway (Razorpay/Stripe)│
│ • 2013-25 PYQ bank  │ • Dynamic timetable │ • Beta launch with 500 aspirants│
│ • Current affairs   │   re-balancer       │ • Performance & security audit  │
│   feed scraper      │ • Spaced repetition │ • App Store & Play Store rollout│
└─────────────────────┴─────────────────────┴─────────────────────────────────┘
```

- **Sprint 1 (Days 1–15)**: Database scaffolding, syllabus taxonomy setup, authentication, and core mobile navigation framework.
- **Sprint 2 (Days 16–30)**: Daily Quiz module with negative scoring, instant explanations, and 2013–2025 PYQ ingestion.
- **Sprint 3 (Days 31–45)**: Current affairs CMS, syllabus tagging microservice, and daily CA quiz generator.
- **Sprint 4 (Days 46–60)**: AI Mains evaluation service integration with OCR, rubric analysis, and model answer comparison.
- **Sprint 5 (Days 61–75)**: Dynamic timetable planner, backlog redistribution algorithms, and study analytics dashboard.
- **Sprint 6 (Days 76–90)**: Performance tuning, dark/light theme polish, offline cache, end-to-end load testing, and production deployment.
