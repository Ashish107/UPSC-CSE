package com.example.data.repository

import com.example.data.model.CurrentAffairsItem
import com.example.data.model.NoteItem
import com.example.data.model.PyqItem
import com.example.data.model.SyllabusItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MasterkeyRepository {

    // Syllabus Flow
    private val _syllabusItems = MutableStateFlow(getInitialSyllabus())
    val syllabusItems: StateFlow<List<SyllabusItem>> = _syllabusItems.asStateFlow()

    // PYQs Flow
    private val _pyqItems = MutableStateFlow(getInitialPyqs())
    val pyqItems: StateFlow<List<PyqItem>> = _pyqItems.asStateFlow()

    // Notes Flow
    private val _noteItems = MutableStateFlow(getInitialNotes())
    val noteItems: StateFlow<List<NoteItem>> = _noteItems.asStateFlow()

    // Current Affairs Flow
    private val _currentAffairsItems = MutableStateFlow(getInitialCurrentAffairs())
    val currentAffairsItems: StateFlow<List<CurrentAffairsItem>> = _currentAffairsItems.asStateFlow()

    // Toggle Syllabus Completion
    fun toggleSyllabusCompletion(id: String) {
        _syllabusItems.update { currentList ->
            currentList.map { item ->
                if (item.id == id) item.copy(isCompleted = !item.isCompleted) else item
            }
        }
    }

    // Dynamic Sync / Content Injection (Mimics live cloud broadcast from Supabase/Firebase)
    fun addNewCurrentAffairs(item: CurrentAffairsItem) {
        _currentAffairsItems.update { listOf(item) + it }
    }

    fun addNewNote(item: NoteItem) {
        _noteItems.update { listOf(item) + it }
    }

    fun addNewPyq(item: PyqItem) {
        _pyqItems.update { listOf(item) + it }
    }

    companion object {
        private fun getInitialSyllabus(): List<SyllabusItem> = listOf(
            SyllabusItem(
                id = "PRE_POLITY_CONST",
                stage = "PRELIMS",
                paper = "GS1",
                subject = "Polity & Governance",
                topicTitle = "Indian Constitution & Political System",
                subtopics = listOf(
                    "Historical Underpinnings & Making of Constitution",
                    "Preamble, Fundamental Rights & Fundamental Duties",
                    "Directive Principles of State Policy (DPSP)",
                    "Basic Structure Doctrine & Judicial Review"
                ),
                isCompleted = true
            ),
            SyllabusItem(
                id = "PRE_POLITY_PARL",
                stage = "PRELIMS",
                paper = "GS1",
                subject = "Polity & Governance",
                topicTitle = "Union Executive & Parliament",
                subtopics = listOf(
                    "President, Vice-President & Governor Discretion",
                    "Parliamentary Sessions, Motions & Committees",
                    "Money Bills, Financial Bills & Ordinance Powers"
                ),
                isCompleted = false
            ),
            SyllabusItem(
                id = "PRE_ENV_BIODIV",
                stage = "PRELIMS",
                paper = "GS1",
                subject = "Environment & Ecology",
                topicTitle = "Biodiversity, Climate Change & Treaties",
                subtopics = listOf(
                    "National Parks, Wildlife Sanctuaries & Biosphere Reserves",
                    "IUCN Red List Threatened Species Categories",
                    "Ramsar Wetlands Convention & CITES Enforcement",
                    "UNFCCC COP Agreements & India's NDC Targets"
                ),
                isCompleted = false
            ),
            SyllabusItem(
                id = "PRE_HIST_MODERN",
                stage = "PRELIMS",
                paper = "GS1",
                subject = "Modern Indian History",
                topicTitle = "Freedom Struggle & National Movement",
                subtopics = listOf(
                    "Revolt of 1857 & Early Resistance Movements",
                    "Socio-Religious Reform Movements",
                    "Indian National Congress: Moderates vs Extremists",
                    "Gandhian Movements (NCM, CDM, Quit India)",
                    "Constitutional Reforms (1909, 1919, 1935)"
                ),
                isCompleted = true
            ),
            SyllabusItem(
                id = "PRE_CSAT_REASON",
                stage = "PRELIMS",
                paper = "CSAT",
                subject = "Aptitude & Reasoning",
                topicTitle = "CSAT Paper II: Comprehension & Logic",
                subtopics = listOf(
                    "Reading Comprehension (Assumptions & Inferences)",
                    "Logical & Analytical Reasoning",
                    "Number Systems, Permutations & Ratio/Proportion"
                ),
                isCompleted = false
            ),
            SyllabusItem(
                id = "MAINS_GS2_FED",
                stage = "MAINS",
                paper = "GS2",
                subject = "Polity & Governance",
                topicTitle = "Federal Structure & Inter-State Relations",
                subtopics = listOf(
                    "Issues & Challenges Pertaining to the Federal Structure",
                    "Role of Governor & Article 200/201 Assent Dynamics",
                    "Inter-State Councils, Water Disputes & GST Council",
                    "Devolution of Powers & Finances up to Local Levels"
                ),
                isCompleted = false
            ),
            SyllabusItem(
                id = "MAINS_GS3_ECO",
                stage = "MAINS",
                paper = "GS3",
                subject = "Economy & Development",
                topicTitle = "Macroeconomic Stability & Monetary Policy",
                subtopics = listOf(
                    "Monetary Policy Framework & MPC Operations",
                    "Fiscal Consolidation & FRBM Act Compliance",
                    "Inflation Dynamics: Headline vs Core CPI",
                    "Inclusive Growth & Employment Generation"
                ),
                isCompleted = false
            ),
            SyllabusItem(
                id = "MAINS_GS4_ETHICS",
                stage = "MAINS",
                paper = "GS4",
                subject = "Ethics, Integrity & Aptitude",
                topicTitle = "Ethics in Public Administration & Case Studies",
                subtopics = listOf(
                    "Essence, Determinants & Consequences of Ethics in Action",
                    "Aptitude & Foundational Values for Civil Services",
                    "Probity in Governance & RTI / Citizen's Charter",
                    "Case Studies on Ethical Dilemmas in Public Life"
                ),
                isCompleted = false
            )
        )

        private fun getInitialPyqs(): List<PyqItem> = listOf(
            PyqItem(
                id = "pyq_2024_polity_1",
                examStage = "PRELIMS",
                examYear = 2024,
                paper = "GS1",
                subject = "Polity & Governance",
                questionText = "Consider the following statements regarding the Inter-State Council:\n" +
                        "1. It is a permanent constitutional body established under Article 263.\n" +
                        "2. The Prime Minister is the Chairman of the Council.\n" +
                        "3. Its recommendations are legally binding on all states.\n\n" +
                        "Which of the statements given above is/are correct?",
                options = mapOf(
                    "A" to "1 and 2 only",
                    "B" to "2 only",
                    "C" to "1 and 3 only",
                    "D" to "1, 2 and 3"
                ),
                correctOption = "B",
                shortExplanation = "• Statement 1 is INCORRECT: Article 263 allows the President to establish the Council, but it is NOT a permanent body (it is convened by presidential order).\n" +
                        "• Statement 2 is CORRECT: The Prime Minister is the Chairman, with Chief Ministers and 6 Union Cabinet Ministers as members.\n" +
                        "• Statement 3 is INCORRECT: Its decisions are consultative and recommendatory, NOT legally binding."
            ),
            PyqItem(
                id = "pyq_2023_env_1",
                examStage = "PRELIMS",
                examYear = 2023,
                paper = "GS1",
                subject = "Environment & Ecology",
                questionText = "Consider the following protected areas:\n" +
                        "1. Bandipur\n" +
                        "2. Bhitarkanika\n" +
                        "3. Manas\n" +
                        "4. Sunderbans\n\n" +
                        "Which of the above are declared Tiger Reserves in India?",
                options = mapOf(
                    "A" to "1 and 2 only",
                    "B" to "1, 3 and 4 only",
                    "C" to "2, 3 and 4 only",
                    "D" to "1, 2, 3 and 4"
                ),
                correctOption = "B",
                shortExplanation = "• Bandipur (Karnataka), Manas (Assam), and Sunderbans (West Bengal) are designated Project Tiger Reserves.\n" +
                        "• Bhitarkanika (Odisha) is a National Park and Ramsar Wetland famed for Saltwater Crocodiles, but is NOT a Tiger Reserve."
            ),
            PyqItem(
                id = "pyq_2022_hist_1",
                examStage = "PRELIMS",
                examYear = 2022,
                paper = "GS1",
                subject = "Modern Indian History",
                questionText = "With reference to Indian history, consider the following statements:\n" +
                        "1. The Dutch established their factories/warehouses on the east coast on lands granted to them by Gajapati rulers.\n" +
                        "2. Alfonso de Albuquerque captured Goa from the Bijapur Sultanate.\n" +
                        "3. The English East India Company established a factory at Madras on a plot of land leased from a representative of the Vijayanagara Empire.\n\n" +
                        "Which of the statements given above are correct?",
                options = mapOf(
                    "A" to "1 and 2 only",
                    "B" to "2 and 3 only",
                    "C" to "1 and 3 only",
                    "D" to "1, 2 and 3"
                ),
                correctOption = "B",
                shortExplanation = "• Statement 1 is INCORRECT: Gajapati rule ended by mid-16th century (1541), while the Dutch arrived in the 17th century.\n" +
                        "• Statement 2 is CORRECT: Albuquerque captured Goa from Sultan of Bijapur (Ismail Adil Shah) in 1510.\n" +
                        "• Statement 3 is CORRECT: Francis Day obtained lease of Madras in 1639 from Damarla Venkatadri Nayaka of Chandragiri (Vijayanagara)."
            ),
            PyqItem(
                id = "pyq_2024_mains_gs2",
                examStage = "MAINS",
                examYear = 2024,
                paper = "GS2",
                subject = "Polity & Governance",
                questionText = "“The institution of the Governor in India has often been a focal point of federal friction between the Union and the States.” Critically examine with reference to recent judicial pronouncements.",
                options = null,
                correctOption = null,
                shortExplanation = "UPSC CSE 2024 GS-2 Question • 150 Words • 10 Marks",
                modelDimensions = listOf(
                    "Introduction: Dual constitutional role of the Governor (Constitutional Head under Art 153-163 vs Agent of Centre).",
                    "Focal Points of Friction: Indefinite delay in bill assent (Article 200), withholding assent without justification, floor test timings, and invocation of Article 356.",
                    "Judicial Dicta: Shamsher Singh (1974), Rameshwar Prasad (2006), and recent Supreme Court ruling mandating Governors cannot exercise pocket veto on state legislation.",
                    "Way Forward: Implement Sarkaria Commission (6-month decision timeframe) & Punchhi Commission recommendations."
                )
            )
        )

        private fun getInitialNotes(): List<NoteItem> = listOf(
            NoteItem(
                id = "note_gov_assent",
                paper = "GS2",
                subject = "Polity & Governance",
                title = "Governor Assent to State Bills & Article 200",
                summaryExcerpt = "Constitutional powers, judicial interpretations, and commission recommendations on gubernatorial assent.",
                definitionContext = "Article 200 outlines the four options available to a Governor when a bill passed by the state legislative assembly is presented: (1) Assent, (2) Withhold assent, (3) Reserve for the President under Article 201, or (4) Return the bill for reconsideration with a message.",
                constitutionalArticles = listOf("Article 200", "Article 201", "Article 163(1)"),
                keyDimensions = listOf(
                    "Constitutional Silence: The phrase 'as soon as possible' lacked a specified statutory deadline, leading to executive-legislative gridlock in several states.",
                    "Aid and Advice Doctrine: Under Article 163(1), Governor is a constitutional figurehead bound by the elected Council of Ministers in ordinary legislative assent.",
                    "The Proviso Mandate: If a returned bill is re-passed by the House (with or without amendment), the Governor has NO discretion and MUST grant assent."
                ),
                wayForward = "Adopt the Sarkaria Commission recommendation fixing a 6-month upper limit, preserving legislative sovereignty without arbitrary obstruction.",
                readTimeMinutes = 3
            ),
            NoteItem(
                id = "note_mpc_framework",
                paper = "GS3",
                subject = "Economy & Development",
                title = "Monetary Policy Committee (MPC) & Flexible Inflation Targeting",
                summaryExcerpt = "Statutory architecture, interest rate transmission, and institutional accountability under the RBI Act.",
                definitionContext = "The Monetary Policy Committee (MPC) is a 6-member statutory body constituted under Section 45ZB of the amended Reserve Bank of India Act, 1934, responsible for determining the policy repo rate necessary to achieve the inflation target.",
                constitutionalArticles = listOf("Section 45ZB (RBI Act)", "Section 45ZA (FIT Target)"),
                keyDimensions = listOf(
                    "Equal Representation: 3 RBI officials (Governor as ex-officio Chair) + 3 independent external experts appointed by the Central Government.",
                    "Dual Mandate: Primary goal is price stability while keeping in mind the objective of economic growth.",
                    "Failure Mechanism: If average inflation deviates from the 2%–6% band for three consecutive quarters, the RBI must submit an explanatory failure report to the Government detailing remedial actions."
                ),
                wayForward = "Reinforce monetary rate signals with structural supply-side logistics and targeted food buffer releases to tackle recurring supply shocks.",
                readTimeMinutes = 4
            ),
            NoteItem(
                id = "note_wetlands_ramsar",
                paper = "GS3",
                subject = "Environment & Ecology",
                title = "Ramsar Wetlands of International Importance in India",
                summaryExcerpt = "Ecological criteria, Montreux Record status, and statutory protection under Wetlands Rules 2017.",
                definitionContext = "The Ramsar Convention (1971, Iran) is an intergovernmental treaty that provides the national and international framework for wetland conservation. India has designated over 80 Ramsar Sites across its biogeographical zones.",
                constitutionalArticles = listOf("Article 48A (DPSP)", "Article 51A(g) (Fundamental Duty)", "Wetlands Rules 2017"),
                keyDimensions = listOf(
                    "Wise Use Principle: Sustainable utilization of wetland ecosystem services without altering ecological character.",
                    "Montreux Record: Register of Ramsar sites where ecological changes have occurred or are likely to occur (currently Keoladeo National Park & Loktak Lake in India).",
                    "Threats: Encroachment, untreated sewage discharge, siltation, and invasive species (water hyacinth)."
                ),
                wayForward = "Empower State Wetlands Authorities (SWA), institute community-led monitoring, and enforce buffer zones around fragile aquatic ecosystems.",
                readTimeMinutes = 3
            )
        )

        private fun getInitialCurrentAffairs(): List<CurrentAffairsItem> = listOf(
            CurrentAffairsItem(
                id = "ca_sc_governor",
                title = "Supreme Court Sets Strict Precedent on Governor Assent to State Bills",
                sourceName = "The Hindu",
                publishedDate = "Today, Oct 19",
                syllabusPaper = "GS-2",
                syllabusTopic = "Executive & Federalism",
                summaryBullets = listOf(
                    "A 3-judge Constitution Bench held that Governors cannot sit indefinitely on bills passed by the State Legislature.",
                    "If a Governor chooses to withhold assent, the bill must be returned to the House immediately with a message for reconsideration.",
                    "Once the legislative assembly passes the bill again (with or without amendments), the Governor is constitutionally bound to grant assent."
                ),
                prelimsFacts = listOf(
                    "Article 200 phrase 'as soon as possible' interpreted as a binding constitutional mandate.",
                    "Shamsher Singh v. State of Punjab (1974) re-affirmed: The Governor does not possess a pocket veto."
                ),
                mainsTakeaway = "Safeguards cooperative federalism by ensuring state legislative supremacy is not paralyzed by un-elected gubernatorial discretion."
            ),
            CurrentAffairsItem(
                id = "ca_rbi_mpc",
                title = "RBI MPC Unanimously Retains Repo Rate at 6.50% Amid Food Inflation Pressures",
                sourceName = "Indian Express",
                publishedDate = "Today, Oct 19",
                syllabusPaper = "GS-3",
                syllabusTopic = "Indian Economy & Banking",
                summaryBullets = listOf(
                    "The Monetary Policy Committee voted unanimously to maintain the benchmark policy repo rate at 6.50%.",
                    "Projected real GDP growth for India held steady at 7.2% for the ongoing fiscal year.",
                    "The Committee emphasized the necessity of durable headline inflation alignment with the 4.0% median target."
                ),
                prelimsFacts = listOf(
                    "MPC formed under Section 45ZB of the Reserve Bank of India Act, 1934.",
                    "Statutory quorum is 4 members; the Governor holds a casting vote in the event of an equality of votes."
                ),
                mainsTakeaway = "Illustrates the tension between stimulating private capital investment and anchoring inflation expectations in emerging market economies."
            ),
            CurrentAffairsItem(
                id = "ca_isa_assembly",
                title = "International Solar Alliance Assembly Endorses Global Mini-Grid Financing Roadmap",
                sourceName = "PIB Delhi",
                publishedDate = "Yesterday, Oct 18",
                syllabusPaper = "GS-3",
                syllabusTopic = "Renewable Energy & Climate",
                summaryBullets = listOf(
                    "Ministers and delegates from over 115 signatory nations endorsed standardized financing for decentralized solar solutions.",
                    "Priority deployment earmarked for agrarian solar pumps and cold-chain infrastructure across the Global South.",
                    "Re-affirmed the 'One Sun One World One Grid' (OSOWOG) cross-border transmission initiative."
                ),
                prelimsFacts = listOf(
                    "Headquarters: Gurugram, India (First treaty-based intergovernmental organization headquartered in India).",
                    "Launched jointly by India and France at COP21 Paris in 2015; target: 1,000 GW solar capacity mobilization by 2030."
                ),
                mainsTakeaway = "Elevates India's leadership in South-South climate cooperation and clean energy technology transfer."
            )
        )
    }
}
