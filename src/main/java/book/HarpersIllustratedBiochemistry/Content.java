/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.HarpersIllustratedBiochemistry;

import backend.SubtopicData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author BRIN
 */
public enum Content {
    
    CHAPTER_1(
            "Biochemistry & Medicine", 
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 1, 1),
                    new SubtopicData("Discovery that a cell-free extract of yeast can ferment sugar", 1, 2),
                    new SubtopicData("Biochemistry & Medicine have provided mutual advances", 2, 2),
                    new SubtopicData("Biochemical Processes underlie Human Health", 2, 4),
                    new SubtopicData("Summary", 4, 4),
                    new SubtopicData("Glossary", 4, 4),
                    new SubtopicData("Appendix", 4, 5)
            )
    ),
    
    CHAPTER_2(
            "Water & pH",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 6, 6),
                    new SubtopicData("Water is an ideal Biologic Solvent", 6, 7),
                    new SubtopicData("Interaction with water influences Structure of Biomolecules", 7, 9),
                    new SubtopicData("Water is an excellent Nucleophile", 9, 10),
                    new SubtopicData("pH is the negactive log of the Hydrogen Ion Concentration", 10, 14),
                    new SubtopicData("Summary", 14, 14)
            )
    ),
    
    CHAPTER_3(
            "Amino Acids & Peptides",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 15, 15),
                    new SubtopicData("Properties of Amino Acids", 16, 19),
                    new SubtopicData("Properties of the Functional Groups of Amino Acids", 19, 21),
                    new SubtopicData("The α-R Groups determine the properties of Amino Acids", 21, 23),
                    new SubtopicData("Summary", 23, 23)
            )
    ),
    
    CHAPTER_4(
            "Proteins: Determination of Primary Structure",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 24, 24),
                    new SubtopicData("Proteins & Peptides must be purified prior to analysis", 24, 27),
                    new SubtopicData("Sanger was the first to determine the Sequence of a Polypeptide", 27, 28),
                    new SubtopicData("Edman devised the first practical method for Peptide Sequencing", 28, 29),
                    new SubtopicData("Molecular Biology revolutionized the determination of primary structure", 29, 29),
                    new SubtopicData("Genomics enables protiens to be identified from small amounts of Sequenced Data", 29, 29),
                    new SubtopicData("Mass Spectrometry can detect covalent modifications", 29, 29),
                    new SubtopicData("Mass Spectrometers come in various configurations", 29, 31),
                    new SubtopicData("Proteomics & the Proteome", 31, 32),
                    new SubtopicData("Summary", 32, 33)
            )
    ),
    
    CHAPTER_5(
            "Proteins: Higher Orders of Structure",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 34, 34),
                    new SubtopicData("Conformation versus Configuration", 34, 35),
                    new SubtopicData("Proteins were initially classified by their Gross Characteristics", 35, 35),
                    new SubtopicData("Proteins are constructed using Modular Principles", 35, 35),
                    new SubtopicData("Four orders of protein structure", 35, 35),
                    new SubtopicData("Secondary Structure", 35, 40),
                    new SubtopicData("Multiple Factors stabilize Tertiary & Quarternary Structure", 40, 40),
                    new SubtopicData("Biophysical Techniques reveal three-dimensional structure", 40, 42),
                    new SubtopicData("Protein Folding", 42, 43),
                    new SubtopicData("Perturbation of protein conformation may have Pathological Consequences", 43, 43),
                    new SubtopicData("Collagen illustrates the role of Post-Translational Processing in protein maturation", 43, 44),
                    new SubtopicData("Summary", 44, 45)
            )
    ),
    
    CHAPTER_6(
            "Proteins: Myoglobin & Haemoglobin",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 49, 50),
                    new SubtopicData("Heme & Ferrous Iron confer the ability to store & transport Oxygen", 50, 51),
                    new SubtopicData("The Oxygen dissociation curves for myoglobin & haemoglobin suit their Physiologic Roles", 51, 52),
                    new SubtopicData("The Allosteric properties of Haemoglobins result from their Quarternary Structures", 52, 56),
                    new SubtopicData("Numerous Mutations affecting Human Haemoglobins have been identified", 56, 56),
                    new SubtopicData("Biomedical Implications", 56, 57),
                    new SubtopicData("Glycated Haemoglobin(HBA1C)", 57, 57),
                    new SubtopicData("Summary", 57, 58)
            )
    ),
    
    CHAPTER_7(
            "Enzymes: Mechanism of Action",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 59, 59),
                    new SubtopicData("Enzymes are efficient & highly specific catalysts", 60, 60),
                    new SubtopicData("Enzymes are classified by Reaction Type", 60, 60),
                    new SubtopicData("Prosthetic Groups, Co-Factors, & Coenzymes play important roles in catalysis", 60, 61),
                    new SubtopicData("Catalysis occurs a the active site", 61, 62),
                    new SubtopicData("Enzymes employ multiple Mechanistic Strategies to facilitate catalysis", 62, 62),
                    new SubtopicData("Substrates induce conformational changes in enzymes", 63, 63),
                    new SubtopicData("HIV Protease illustrates acid-base catalysis", 63, 63),
                    new SubtopicData("Chymotrypsin & Fructose-2,6-bisphosphatase illustrate covalent catalysis", 63, 64),
                    new SubtopicData("Catalytic residues are highly Conserved", 64, 64),
                    new SubtopicData("Isozymes are distinct enzymes forms that catalyse the same reaction", 64, 65),
                    new SubtopicData("The catalytic activity of enzymes facilitates their detection", 65, 66),
                    new SubtopicData("The Analysis of certain enzyme aid diagnosis", 66, 68),
                    new SubtopicData("Enzymes facilitate diagnosis of genetic & infectitious disease", 68, 68),
                    new SubtopicData("Recombinant DNA provides an important tool for studying enzymes", 68, 69),
                    new SubtopicData("Ribozymes: Artifacts from RNA World", 69, 70),
                    new SubtopicData("Summary", 70, 70)
            )
    ),
    
    CHAPTER_8(
            "Enzymes: Kinetics",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 71, 72),
                    new SubtopicData("Chemical Reactions described using Balanced Equations", 72, 72),
                    new SubtopicData("Changes in Free Energy determine the direction & Equilibrium state of chemical reactions", 72, 72),
                    new SubtopicData("The rates of reactions are determined by their activation energy", 72, 73),
                    new SubtopicData("Numerous Factors affect Reation Rate", 73, 75),
                    new SubtopicData("The kinetics of enzyme catalysis", 75, 75),
                    new SubtopicData("Enzymes do not affect K_eq", 75, 75),
                    new SubtopicData("Multiple factors affect the rates of enzyme-catalysed reactions", 75, 76),
                    new SubtopicData("Assays of enzyme-catalysed reactions typically measure the Initial Velocity", 76, 76),
                    new SubtopicData("Substrate Concentration affect the reaction rate", 76, 76),
                    new SubtopicData("The Michaelis-Menten & Hill Equations model the effects of substrate concentration", 76, 79),
                    new SubtopicData("Kinetic analysis distinguishes Competitive from Non-Competitive Inhibition", 79, 82),
                    new SubtopicData("Most enzyme-catalysed reactions involve two or more substrates", 82, 83),
                    new SubtopicData("Knowledge of enzyme kinetics, mechanism, and inhibition aids drug development", 83, 83),
                    new SubtopicData("Summary", 83, 84)
            )
    ),
    
    CHAPTER_9(
            "Enzymes: Regulation of Activities",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 85, 86),
                    new SubtopicData("Regulation of metabolite flow can be active or passive", 86, 86),
                    new SubtopicData("Compartmentation ensures metabolic efficiency & simplifies regulation", 86, 87),
                    new SubtopicData("Regulation of enzyme quantity", 87, 88),
                    new SubtopicData("Multiple options are available for regulating catalytic activity", 88, 88),
                    new SubtopicData("Allosteric effectors regulate certain enzymes", 88, 89),
                    new SubtopicData("Aspartate Transcarbomylase", 90, 90),
                    new SubtopicData("Regulatory Covalent Modifications can be reversible or irreversible", 90, 91),
                    new SubtopicData("Reversible Covalent Modifications regulates key Mammalian Proteins", 92, 93),
                    new SubtopicData("Protein Phophorylation is extremely versatile", 93, 94),
                    new SubtopicData("Individual regulatory events combine to form sophisticated control networks", 94, 94),
                    new SubtopicData("Summary", 94, 95)
            )
    ),
    
    CHAPTER_10(
            "The Biochemical Roles of Transition Metals",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 96, 96),
                    new SubtopicData("Transition Metals are essential for health", 97, 97),
                    new SubtopicData("Toxicity of Heavy Metals", 97, 98),
                    new SubtopicData("Toxicity of Transition Metals", 98, 99),
                    new SubtopicData("Living organisms package transition metals within Organometallic Complexes", 99, 100),
                    new SubtopicData("Physiologic roles of the essential transition metals", 100, 101),
                    new SubtopicData("Absorption & Transport of Transition Metals", 105, 105),
                    new SubtopicData("Summary", 105, 106)
            )
    ),
    
    CHAPTER_11(
            "Bioenergetics: The Role of ATP",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 109, 109),
                    new SubtopicData("Free Energy is the useful energy in a system", 109, 110),
                    new SubtopicData("Endergonic Processes proceed by Coupling to Exergonic Processess", 110, 111),
                    new SubtopicData("High-Energy phosphates play a central role in energy capture & transfer", 111, 112),
                    new SubtopicData("ATP acts as the \"Energy Currency\" of the cell", 112, 114),
                    new SubtopicData("Summary", 114, 114)
            )
    ),
    
    CHAPTER_12(
            "Biologic Oxidation",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 115, 115),
                    new SubtopicData("Free Energy changes can be expressed in terms of Redox Potential", 115, 116),
                    new SubtopicData("Oxidase uses Oxygen as a Hydrogen Acceptor", 116, 116),
                    new SubtopicData("Dehydrogenase performs two main functions", 116, 118),
                    new SubtopicData("Hydroperoxidasees use Hydrogen Peroxide or an Organic Peroxide as a substrate", 118, 118),
                    new SubtopicData("Oxygenase catalyse the direct transfer & incorporation of Oxygen into substrate molecule", 119, 120),
                    new SubtopicData("Superoxide Dismutase protects aerobic organisms against oxygen toxicity", 120, 120),
                    new SubtopicData("Summary", 120, 120)
            )
    ),
    
    CHAPTER_13(
            "The Respiratory Chain & Oxidative Phosphorylation",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 122, 122),
                    new SubtopicData("Specific Enzymes are associated with compartments separated by the Mitochondrian Membranes", 122, 122),
                    new SubtopicData("The respiratory chain oxidases reducing equivalents & acts as a Proton Pump.", 122, 125),
                    new SubtopicData("Electron Transport via the respiratory chain create a Proton Gradient which drives the synthesis of ATP", 125, 125),
                    new SubtopicData("The Respiratory Chain provides most of the energy captured during Catabolism", 125, 127),
                    new SubtopicData("Many Poisons inhibit the respiratory chain", 127, 128),
                    new SubtopicData("The Chemiosmotic Theory can account for respiratory control and the action of uncouplers", 128, 128),
                    new SubtopicData("The Selective Permeability of the Inner Mitochondrial membrane necessitates exchange transporters", 128, 130),
                    new SubtopicData("Clinical Aspects", 130, 130),
                    new SubtopicData("Summary", 130, 130)
            )
    ),
    
    CHAPTER_14(
            "Overview of Metabolism & the Provision of Metabolic Fuels",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 133, 134),
                    new SubtopicData("Overview of substrate metabolism in Fasting & Feasting", 134, 135),
                    new SubtopicData("Pathways to process the major products of our diet", 135, 136),
                    new SubtopicData("Metabolic Pathways at the Organ & Cellular Level", 136, 139),
                    new SubtopicData("The flux through metabolic pathways must be regulated in a Concerted Manner", 139, 141),
                    new SubtopicData("Allosteric & Hormonal signals control of enzyme-catalysed reactions", 141, 141),
                    new SubtopicData("Many netabolic fuels are Inter-convertible", 141, 142),
                    new SubtopicData("A supply of oxidizable fuel is provided in both the Fed & Fasting states", 142, 145),
                    new SubtopicData("Clinical Aspect", 145, 146),
                    new SubtopicData("Summary", 146, 146)
            )
    ),
    
    CHAPTER_15(
            "Saccharides(ie, Carbohydrates) of Physiological Importance",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 147, 148),
                    new SubtopicData("Saccharides are Aldehyde or Ketone derivatives of Polyhydric Alcohols", 148, 148),
                    new SubtopicData("Biomedically, Glucose is the most important Monosaccharide", 148, 152),
                    new SubtopicData("Polysaccharides serve Storage & Structural functions", 152, 155),
                    new SubtopicData("Carbohydrates occur in Cell Membranes & in Lipoprotiens", 155, 155),
                    new SubtopicData("Summary", 155, 155)
            )
    ),
    
    CHAPTER_16(
            "The Citric Acid Cycle: A Pathway Central to Carbohydrate, Lipid, & Amino Acid Metabolism",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 156, 156),
                    new SubtopicData("The Citric Acid Cycle provides substrates for the Respiratory Chain", 156, 157),
                    new SubtopicData("Reactions of the citric acid cycle generate Reducing Equivalents & CO2", 157, 159),
                    new SubtopicData("Ten ATP are formed per turn of the Citric Acid Cycle", 159, 159),
                    new SubtopicData("Vitamins play key roles in the Citric Acid Cycle", 159, 159),
                    new SubtopicData("The Citric Acid Cycle plays a pivotal role in metabolism", 159, 162),
                    new SubtopicData("Summary", 162, 162)
            )
    ),
    
    CHAPTER_17(
            "Glycolysis & the Oxidation of Pyruvate",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 163, 163),
                    new SubtopicData("Glycolysis can function under Anaerobic Conditions", 163, 164),
                    new SubtopicData("Glycolysis constitues the main pathway of glucose utilization", 164, 167),
                    new SubtopicData("Tissues that function under Hypoxic conditions of have Intrisically High rates of glucose oxidation produce Lactate", 167, 167),
                    new SubtopicData("Glycolysis is regulated at three Non-Equilibrium reactions", 167, 168),
                    new SubtopicData("The Oxidation of Pyruvate to Acetyl-COA is the Irreversible Route from Glycolysis to Citric Acid Cycle", 168, 168),
                    new SubtopicData("Clinical Aspects", 168, 170),
                    new SubtopicData("Summary", 170, 170)
            )
    ),
    
    CHAPTER_18(
            "Metabolism of Glucose",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 171, 172),
                    new SubtopicData("Glycogenesis occurs mainly in Muscle & Liver", 173, 173),
                    new SubtopicData("Glycogenolysis is not the reverse of glycogenesis, it is a separate pathway", 173, 175),
                    new SubtopicData("Cyclic AMP integrates the regulation of Glycogenolysis & Glycogenesis", 175, 175),
                    new SubtopicData("cAMP activates glycogen phosphorylase", 175, 177),
                    new SubtopicData("Glycogen metabolism is regulated by a balance in activities between Glycogen Synthase & Phosphorylase", 177, 178),
                    new SubtopicData("Clinical Aspects", 178, 179),
                    new SubtopicData("Summary", 179, 179)
                    
            )
    ),
    
    CHAPTER_19(
            "Gluconeogenesis & the Control of Blood Glucose",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 180, 181),
                    new SubtopicData("Gluconeogenesis involves glycolysis, the citric acid cycle, plus some special reactions", 181, 183),
                    new SubtopicData("Glycolysis & Gluconeogenesis share the same pathway but in Opposite Directions, & are Reciprocally Regulated", 183, 185),
                    new SubtopicData("The blood concentration of Glucose is regulated within Narrow Limits", 185, 186),
                    new SubtopicData("Blood glucose is derived from the Diet, Gluconeogenesis, & Glycogenolysis", 186, 188),
                    new SubtopicData("Further Clinical Aspects", 189, 190),
                    new SubtopicData("Summary", 190, 190)
            )
    ),
    
    CHAPTER_20(
            "The Pentose Phosphate Pathway & Other Pathways of Hexose Metabolism",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 191, 191),
                    new SubtopicData("The Pentose Phosphate Pathway forms NADPH & Ribose Phosphate", 191, 192),
                    new SubtopicData("Reactions of the pentose phosphate pathway occur in the Cytosol", 192, 194),
                    new SubtopicData("The Pentose Phosphate Pathway & Glutathione Peroxidase protect erythrocytes against hemolysis", 194, 195),
                    new SubtopicData("Glucuronate, A Precursor of Proteoglycans & Conjugated Glucuronides, is a product of the Uronic Aicd Pathway", 195, 195),
                    new SubtopicData("Ingestion of large quatities of Fructose has profound metabolic consequences", 195, 196),
                    new SubtopicData("Galactose is needed for the synthesis of Lactose, Glycolipids, Proteoglycans, & Glycoproteins", 197, 198),
                    new SubtopicData("Clinical Aspects", 198, 200),
                    new SubtopicData("Summary", 200, 200)
            )
    ),
    
    CHAPTER_21(
            "Lipids of Physiologic Significance",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 206, 206),
                    new SubtopicData("Lipids maybe simple, complex or derived", 206, 206),
                    new SubtopicData("Fatty acids are aliphatic carboxylic acids", 206, 209),
                    new SubtopicData("Triacylglycerols (Triglycerides) are the main Storage Forms of fatty acids", 209, 209),
                    new SubtopicData("Phospholipids are the main lipid constituents of membranes", 209, 212),
                    new SubtopicData("Glycolipids (Glycosphingolipids) are important in Nerve Tissues & in the Cell Membrane", 212, 212),
                    new SubtopicData("Steroids play many physiologically important roles", 212, 213),
                    new SubtopicData("Lipid Peroxidation is a source of Free Radicals", 213, 214),
                    new SubtopicData("Amphipathic Lipids self-orient at oil:water interface", 215, 215),
                    new SubtopicData("Summary", 216, 216)
            )
    ),
    
    CHAPTER_22(
            "Oxidation of Fatty Acids: Ketogenesis",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 217, 217),
                    new SubtopicData("Oxidation of fatty acids occurs in mitochondria", 217, 218),
                    new SubtopicData("ß-Oxidation of fatty acids involves successive cleavage with release of Acetyl-COA", 218, 220),
                    new SubtopicData("Ketogenesis occurs when there is a High Rate of fatty acids oxidation in  the Liver", 220, 223),
                    new SubtopicData("Ketogenesis is regulated at three crucial steps", 223, 224),
                    new SubtopicData("Clinical Aspects", 224, 225),
                    new SubtopicData("Summary", 225, 225)
            )
    ),
    
    CHAPTER_23(
            "Biosynthesis of Fatty Acids & Eicosanoids",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 226, 226),
                    new SubtopicData("The Main Pathway for De-Novo synthesis of fatty acids (Lipogenesis) occurs in the Cytosol", 226, 230),
                    new SubtopicData("The nutritional state regualtes lipogenesis", 230, 230),
                    new SubtopicData("Short- & Long-Term mechanisms regualate lipogenesis", 230, 232),
                    new SubtopicData("Some Polyunsaturated fatty acids can not be synthesized by Mammals & are Nutritionally Essential", 232, 233),
                    new SubtopicData("Monounsaturated fatty acids are synthesized by a δ-9 Desaturase System", 233, 233),
                    new SubtopicData("Synthesis of Polyunsaturated Fatty Acids involves Desatirase & Elongase Enzyme Systems", 233, 234),
                    new SubtopicData("The essential fatty acids (EFA) have important body functions in the body", 234, 234),
                    new SubtopicData("Eicosanoids are formed from C20 Polyunsaturated Fatty Acids", 234, 234),
                    new SubtopicData("The Cyclooxygenase pathway is responsible for prostanoid synthesis", 234, 235),
                    new SubtopicData("Leukotrienes & Lipoxins are formed by the Lipoxygenase Pathway", 235, 235),
                    new SubtopicData("Clinical Aspects", 235, 238),
                    new SubtopicData("Summary", 238, 238)
            )
    ),
    
    CHAPTER_24(
            "Metabolism of Acyglycerols & Sphingolipids",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 239, 239),
                    new SubtopicData("Hydrolysis initiates Catabolism of Triacylglycerols", 239, 239),
                    new SubtopicData("Trigkycerols & Phosphoglycerols are formed by acylation of triose phosphates", 240, 243),
                    new SubtopicData("All sphingolipids are formed from ceramide", 243, 244),
                    new SubtopicData("Clinical Aspects", 244, 245),
                    new SubtopicData("Summary", 245, 245)
            )
    ),
    
    CHAPTER_25(
            "Lipid Transport & Storage",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 247, 248),
                    new SubtopicData("Lipids are transported in the Plasma as Lipoproteins", 248, 249),
                    new SubtopicData("Free fatty acids are readily metabolized", 249, 249),
                    new SubtopicData("Triacylglycerol is transported from the intestines in Chylomicrons & from liver in Very-Low-Density Lipoproteins", 249, 251),
                    new SubtopicData("Chylomicrons & Very-Low-Density Lipoproteins are rapidly catabolized", 251, 252),
                    new SubtopicData("LDL is metabolized via the LDL receptor", 252, 252),
                    new SubtopicData("HDL takes part in both Lipoprotein triacylglycerol & cholestrol metabolism", 252, 253),
                    new SubtopicData("The liver plays a central role in lipid transport & metabolism", 253, 254),
                    new SubtopicData("Clinical Aspects", 254, 255),
                    new SubtopicData("Adipose Tissue is the main store of Triglycerol in the body", 255, 256),
                    new SubtopicData("Hormones regulate fat metabolization", 256, 257),
                    new SubtopicData("Brown Adipose Tissue promotes thermogenesis", 257, 258),
                    new SubtopicData("Summary", 258, 258)
            )
    ),
    
    CHAPTER_26(
            "Cholestrol Synthesis, Transport, & Excretion",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 259, 260),
                    new SubtopicData("Cholestrol is biosynthesized from Acetyl-COA", 260, 262),
                    new SubtopicData("Cholestrol synthesis is controlled by regulation of HMG-CoA Reductase", 262, 263),
                    new SubtopicData("The cholestrol balance in tissues is Tightly Regulated", 263, 264),
                    new SubtopicData("Cholestrol is transposrted between tissues in plasma lipoproteins", 264, 264),
                    new SubtopicData("Cholestrol is excreted from the body in the bile as cholestrol or bile acids", 265, 266),
                    new SubtopicData("Clinical Aspects", 267, 268),
                    new SubtopicData("Summary", 268, 269)
            )
    ),
    
    CHAPTER_27(
            "Biosynthesis of the Nutrionally Non-Essential Amino Acids",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 273, 274),
                    new SubtopicData("Nutritionally essential & nutritionally non-essential amino acids", 274, 274),
                    new SubtopicData("Biosynthesis of the nutritionally non-essential amino acids", 275, 278),
                    new SubtopicData("Summary", 278, 278)
            )
    ),
    
    CHAPTER_28(
            "Catabolism of Proteins & of Amino Acid Nitrogen",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 279, 279),
                    new SubtopicData("Protein Turnover", 279, 280),
                    new SubtopicData("Proteases & Peptidases degrade proteins to amino acids", 280, 281),
                    new SubtopicData("Interorgan exchange maintains circulating levels of amino acids", 281, 282),
                    new SubtopicData("Animals convert α-Amino Nitrogen to varied end products", 282, 282),
                    new SubtopicData("Biosynthesis of Urea", 282, 283),
                    new SubtopicData("L-Glutamate dehydrogenase occupies a central position in Nitrogen Metabolism", 283, 283),
                    new SubtopicData("Amino Acid oxidases remove Nitrogen as Ammonia", 283, 286),
                    new SubtopicData("General Features of Metabolic Disorders", 286, 287),
                    new SubtopicData("Metabolic Disorders are associated with Each Reaction of Urea Cycle", 287, 288),
                    new SubtopicData("Summary", 288, 288)
            )
    ),
    CHAPTER_29(
            "Catabolism of the Carbon Skeletons of Amino Acids",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 290, 291),
                    new SubtopicData("Amino Acids are catabolized to intermediates for Carbohydrate & Lipid Biosynthesis", 291, 291),
                    new SubtopicData("Transamination typically initiates amino acid catabolism", 291, 293),
                    new SubtopicData("Catabolism of Glycine, Serine, Alanine, Cysteine, Threonine & 4-Hydroxyproline", 293, 296),
                    new SubtopicData("Additional amino acids that form Acetyl-CoA", 296, 298),
                    new SubtopicData("The initial reactions are common to all three Branched-Chain Amino Acids", 298, 300),
                    new SubtopicData("Metabolic Disorders of Branched-Chain Amino Acid catabolism", 300, 304),
                    new SubtopicData("Summary", 304, 304)
            )
    ),
    
    CHAPTER_30(
            "Conversion of Amino Acids to Specialized Products",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 306, 306),
                    new SubtopicData("L-α-Amino Acids", 306, 312),
                    new SubtopicData("Non-α-Amino Acids", 312, 313),
                    new SubtopicData("Summary", 313, 314)
            )
    ),
    
    CHAPTER_31(
            "Pprphyrins & Bile Pigments",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 315, 315),
                    new SubtopicData("Porphyrins", 315, 315),
                    new SubtopicData("Heme is synthesized from Succinyl-CoA & Glycine", 315, 318),
                    new SubtopicData("Porphyrins are colored & Fluoresce", 318, 319),
                    new SubtopicData("Disorders of Heme Biosynthesis", 319, 321),
                    new SubtopicData("Classification of the Porphyrias", 321, 321),
                    new SubtopicData("Catabolism of the Heme produces Bilirubin", 321, 323),
                    new SubtopicData("Hyperbilirubinemia causes Jaundice", 323, 324),
                    new SubtopicData("Disorders of Bilirubin Metabolism", 324, 325),
                    new SubtopicData("Summary", 325, 326)
            )
    ),
    
    CHAPTER_32(
            "Nucleotides",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 330, 330),
                    new SubtopicData("Chemistry of Purines, Pyrimidines, Nucleosides, & Nucleotides", 330, 334),
                    new SubtopicData("Synthetic nucleotides analogs are used in Chemotherapy", 334, 335),
                    new SubtopicData("DNA & RNA are polynucleotides", 335, 335),
                    new SubtopicData("Summary", 335, 336)
            )
    ),
    
    CHAPTER_33(
            "Metabolism of Purine & Pyrimidine Nucleotides",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 337,338),
                    new SubtopicData("Purines & Pyrimidines are dietarily non-essential", 338, 338),
                    new SubtopicData("Biosynthesis of purine nucleotides", 338, 338),
                    new SubtopicData("Inosine Monophosphate (IMP) is synthesized from Amphibolic Intermediates", 338, 340),
                    new SubtopicData("\"Salvage Reactios\" convert purine and their nucleosides to mononucleotides", 340, 340),
                    new SubtopicData("Hepatic purine biosynthesis is Strigently Regulated", 340, 341),
                    new SubtopicData("Reduction of Ribonucleoside Diphosphates fors Deoxyribonucleoside Diphosphate", 342, 342),
                    new SubtopicData("Biosynthesis of Pyrimidine Nucleotides", 342, 342),
                    new SubtopicData("The deoxyribonucleosides of Uracil & Cytosine are salvaged", 342, 344),
                    new SubtopicData("Regulation of Pyrimidine Nucleotide Biosynthesis", 344, 344),
                    new SubtopicData("Humans catabolize Purines to Uric Acid", 344, 344),
                    new SubtopicData("Disorders of Purine Metabolism", 344, 345),
                    new SubtopicData("Pyrimidine catabolites are water soluble", 345, 346),
                    new SubtopicData("Overproduction of Pyrimidine Catabolites", 346, 347),
                    new SubtopicData("Summary", 347, 347)
                    
            )
    ),
    
    CHAPTER_34(
            "Nucleic Acid Structure & Function",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 348, 348),
                    new SubtopicData("DNA contains the genetic information", 348, 351),
                    new SubtopicData("DNA provides a template for Replication & Transcription", 351, 352),
                    new SubtopicData("The chemical nature of RNA differs from that of DNA", 352, 354),
                    new SubtopicData("Nearly all the several species of stable, abundant RNAs are involved in some aspect of Protein Synthesis", 354, 354),
                    new SubtopicData("There exists several distinct classes of RNA", 355, 359),
                    new SubtopicData("Specific nucleases digest nucleic acids", 359, 359),
                    new SubtopicData("Summary", 359, 359)
                    
            )
    ),
    
    CHAPTER_35(
            "DNA Organisation, Replication, & Repair",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 360, 361),
                    new SubtopicData("Chromatin is the chromosomal material in the nuclei of cells of eukaryotic organisms", 361, 363),
                    new SubtopicData("Higher order structures provide for the compaction of chromatin", 363, 364),
                    new SubtopicData("Some regions of Chromatin are \"Active\" & others are \"Inactive\"", 364, 364),
                    new SubtopicData("DNA is organized into chromosomes", 364, 366),
                    new SubtopicData("The exact function of much of the mammalian genome is not well understood", 366, 368),
                    new SubtopicData("One percent of cellular DNA is in Mitochondria", 368, 368),
                    new SubtopicData("Genetic material can be Altered and Rearranged", 368, 371),
                    new SubtopicData("DNA Synthesis & Replication are rigidly controlled", 371, 382),
                    new SubtopicData("Summary", 382, 382)
                    
            )
    ),
    
    CHAPTER_36(
            "RNA Synthesis, Processing, & Modification",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 384, 384),
                    new SubtopicData("RNA exists in two major classes", 384, 385),
                    new SubtopicData("RNA is synthesized from a DNA template by RNA Polymerases", 385, 388),
                    new SubtopicData("RNA Synthesis is a cyclical process that involves RNA chain initiation, elongation, & termination", 388, 389),
                    new SubtopicData("The fidelity & Frequency of transcription us controlled by proteins bound to certian DNA Seqences", 389, 394),
                    new SubtopicData("The Eukaryotic transcription machinery is complex", 394, 397),
                    new SubtopicData("RNA Molecules are extensively processed before they become functional", 397, 400),
                    new SubtopicData("RNAs can be extensively modified", 400, 402),
                    new SubtopicData("RNA can act as a catalyst", 402, 402),
                    new SubtopicData("Summary", 402, 402),
                    new SubtopicData("Summary", 402, 403)
                    
            )
    ),
    
    CHAPTER_37(
            "Protein Synthesis & the Genetic Code",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 404, 404),
                    new SubtopicData("Genetic information flows from DNA to RNA to protein", 404, 405),
                    new SubtopicData("The Nucleotide sequence of an mRNA molecule contains a series of codons that specify the amino acid sequence of the encoded protein", 405, 405),
                    new SubtopicData("The Genetic Code is Degenerate, Unambiguous, Non-Overlapping, without Punctuation, & Universal", 405, 406),
                    new SubtopicData("At least one species of tRNA exists for each of the 20 amino acids", 406, 407),
                    new SubtopicData("Mutations result when changes occur in the nucleotide sequence", 407, 410),
                    new SubtopicData("Like Transcription, Protein synthesis can be described in three phases: Initiation, Elongation, & Termination", 410, 417),
                    new SubtopicData("Post-Translational processing affects the activity of many proteins", 417, 417),
                    new SubtopicData("Many Antibiotics work by selectively inhibiting protein synthesis in bacteria", 417, 418),
                    new SubtopicData("Summary", 418, 418)
            )
    ),
    
    CHAPTER_38(
            "Regulation of Gene Expression",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 420, 421),
                    new SubtopicData("Regulated expression of genes is required for Development, Differentiation, & Adaptation", 421, 421),
                    new SubtopicData("Biologic systems exhibit three types of temporal responses to a regulatory signal", 421, 429),
                    new SubtopicData("Special Features are involved in regulation of Eukaryotic gene transcription", 429, 437),
                    new SubtopicData("Several Structural motifs compose the DNA-Binding domains of Regulatory transcription factor proteins", 437, 438),
                    new SubtopicData("The DNA Binding & Transactivation domains of most regulatory proteins are separate", 438, 439),
                    new SubtopicData("Gene regualation in prokaryotes & eukaryotes differs in other important respects", 439, 442),
                    new SubtopicData("Summary", 442, 443)
            )
    ),
    
    CHAPTER_39(
            "Molecular Genetics, Recombinant DNA & Genomic Technology",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 444, 444),
                    new SubtopicData("Recombinant DNA technology involves isolation & manupulation of DNA to make chimeric molecules", 444, 453),
                    new SubtopicData("Practical Applications of Recombinant DNA Technology are numerous", 453, 455),
                    new SubtopicData("Systems biology aims to integrate the flood of -omic data in order to decipher fundamental biologic regulatory principles", 455, 457),
                    new SubtopicData("Summary", 457, 457)
            )
    ),
    
    CHAPTER_40(
            "Membranes: Structure and Function",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 467, 467),
                    new SubtopicData("Maintainence of a normal intra- & extracellular environment is fundamental to life", 468, 468),
                    new SubtopicData("Membranes are complex structures composed of Lipids, Proteins, & Carbohydrate - containing molecules", 468, 473),
                    new SubtopicData("Artificial Membranes model membrane function", 473, 473),
                    new SubtopicData("The Fluid Mossaic Model of membrane structure is widely accepted", 473, 474),
                    new SubtopicData("Membrane selectivity allows adjustments of cell composition & function", 474, 480),
                    new SubtopicData("Active transport system require a source of energy", 480, 480),
                    new SubtopicData("Transmission of Nerve impulses involves Ion Channels & Pumps", 481, 481),
                    new SubtopicData("Transport of glucose involves several mechanisms", 481, 481),
                    new SubtopicData("Cells transport certain macromolecules across the plasma membrane by endocytosis & exocytosis", 481, 483),
                    new SubtopicData("Various signals can be transmitted across membranes", 483, 483),
                    new SubtopicData("Gap junctions allow direct flow of molecules from one cell to another", 483, 483),
                    new SubtopicData("Extracellular vesicles (exosomes) represent a novel, & previously underappreciated mechanism of cell-cell communication", 483, 484),
                    new SubtopicData("Mutations affecting Membrane Proteins cause diseases", 484, 486),
                    new SubtopicData("Summary", 486, 486)
            )
    ),
    
    CHAPTER_41(
            "The Diversity of Endocrine System",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 488, 488),
                    new SubtopicData("Teh target cell concept", 489, 489),
                    new SubtopicData("Hormone receptors are of central importance", 489, 490),
                    new SubtopicData("Hormones can be classified in several ways", 490, 491),
                    new SubtopicData("Diversity of the endocrine system", 491, 493),
                    new SubtopicData("Many Hormones are made from cholesterol", 493, 499),
                    new SubtopicData("Catecholamines & Thyroid hormones are made from tyrosine", 499, 505),
                    new SubtopicData("There is significant variation in the storage & secretion of hormones", 505, 506),
                    new SubtopicData("Some hormones have plasma transport proteins", 506, 507),
                    new SubtopicData("Summary", 507, 507)
            )
    ),
    
    CHAPTER_42(
            "Hormone Action & Signal Transduction",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 508, 508),
                    new SubtopicData("Hormones transduce signals to affect Homeostatic mechanisms", 508, 509),
                    new SubtopicData("Signal Generation", 509, 510),
                    new SubtopicData("Group II (Peptide & Catecholamine) Hormones have membrane receptors & Use intracellular messengers", 510, 519),
                    new SubtopicData("Hormones can influence specific biologic effects by Modulating Transcription", 519, 522),
                    new SubtopicData("Summary", 522, 523)
            )
    ),
    
    CHAPTER_43(
            "Nutrition, Digestion, & Absorption",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 527, 527),
                    new SubtopicData("Digestion & Absorption of Carbohydrates", 528, 528),
                    new SubtopicData("Digestion & Absorption of Lipids", 528, 529),
                    new SubtopicData("Digestion & Absorption of Proteins", 529, 531),
                    new SubtopicData("Digestion & Absorption of Vitamins & Minerals", 531, 532),
                    new SubtopicData("Energy Balance: Over- & Undernutrition", 532, 533),
                    new SubtopicData("Protein & Amino Acid requirements", 533, 534),
                    new SubtopicData("Summary", 534, 534)
            )
    ),
    
    CHAPTER_44(
            "Micronutrients: Vitamins & Minerals",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 535, 536),
                    new SubtopicData("The Vitamins are a disparate group of compounds with a variety of metabolic functions", 536, 536),
                    new SubtopicData("Lipid Soluble Vitamins: Two Groups of Compounds have Vitamin A activity", 537, 538),
                    new SubtopicData("Lipid Soluble Vitamins: Vitamin D is really a hormone", 538, 540),
                    new SubtopicData("Lipid Soluble VItamins: Vitamin E does not have a precisely defined metabloic function", 540, 540),
                    new SubtopicData("Lipid Soluble Vitamins: Vitamin K is required for synthesis of Blood-Clotting proteins", 540, 541),
                    new SubtopicData("Water Soluble Vitamins: Vitamin B1 (Thiamin) has a key role in carbohydrate metabolism", 541, 542),
                    new SubtopicData("Water Soluble Vitamins: Vitamin B2 (Riboflavin) has a central role in energy yielding metabolism", 542, 542),
                    new SubtopicData("Water Soluble Vitamins: Niacin is not strictly a vitamin", 542, 543),
                    new SubtopicData("Water Soluble Vitamins: Vitamin B6 is important in amino acid & glycogen metabolism & in steroid hormone action", 543, 543),
                    new SubtopicData("Water Soluble Vitamins: Vitamin B12 is found only in foods of animal origin", 544, 545),
                    new SubtopicData("Water Soluble Vitamins: There are multiple forms of folate in the diet", 545, 546),
                    new SubtopicData("Water Soluble Vitamins: Dietary biotin deficiency is unknown", 546, 547),
                    new SubtopicData("Water Soluble Vitamins: As part of Coenzyme A & ACP, Pantothenic acid acts a carrier of acyl groups", 547, 547),
                    new SubtopicData("Water Soluble Vitamins: Ascorbic acid is a vitamin for only some species", 547, 548),
                    new SubtopicData("Minerals are required for both physiologic biochemical functions", 548, 548),
                    new SubtopicData("Summary", 548, 548)
            )
    ),
    
    CHAPTER_45(
            "Free Radicals & Antioxidant Nutrients",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 549, 553),
                    new SubtopicData("Summary", 553, 553)
            )
    ),
    
    CHAPTER_46(
            "Glycoproteins",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 554, 554),
                    new SubtopicData("Glycoproteins occurs widely & perform numerous functions", 554, 554),
                    new SubtopicData("Oligosaccharide chains encode biological information", 554, 555),
                    new SubtopicData("Eight sugars predominate in human glycoproteins", 555, 555),
                    new SubtopicData("Lectins can be used to purify glycoproteins & to investigate their funtions", 555, 555),
                    new SubtopicData("There are three major classes of glycoproteins", 555, 557),
                    new SubtopicData("Glycoproteins contain several types of O-Glycosidic Linkages", 557, 558),
                    new SubtopicData("N-Linked glycoproteins contain an Asparagine-N-Acetylglucosamine linkage", 558, 560),
                    new SubtopicData("Some proteins are anchored to the plasma membrane by Glycophosphotidyl-Inositol structures", 560, 560),
                    new SubtopicData("Some proteins undergo rapidly Reversible Glycosylation", 560, 561),
                    new SubtopicData("Advanced Glycation End-products (AGEs) are important in causing tissue damage in Diabetes Mellitus", 561, 561),
                    new SubtopicData("Glycoproteins are involved in many biological processes & in many diseases", 561, 563),
                    new SubtopicData("Glycans are involved in the binding of viruses, bacteria, & some parasites to human cells", 563, 563),
                    new SubtopicData("Summary", 563, 563)
            )
    ),
    
    CHAPTER_47(
            "Metabolism of Xenobiotics",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical importance", 564, 564),
                    new SubtopicData("We encounter many xenobiotics that must be metabolized before being excreted", 564, 564),
                    new SubtopicData("Isoforms of cytochrome P450 Hydroxylate a wide variety of xenobiotics in Phase 1 Metabolism", 565, 566),
                    new SubtopicData("Conjugation reactions in phase 2 metabolism prepare xenobiotics for excretion", 566, 566),
                    new SubtopicData("Other reactions are also involved in Phase 2 Metabolism", 566, 567),
                    new SubtopicData("Response to xenobiotics include Toxic, Immunological, & Carcinogenic Effects", 567, 567),
                    new SubtopicData("Summary", 567, 567)
            )
    ),
    
    CHAPTER_48(
            "Clinical Biochemistry",
            Arrays.asList(
                    
                    new SubtopicData("The importance of Laboratory Tests in Medicine", 568, 568),
                    new SubtopicData("Causes of abnormalities in levels of analytes measured in the laboratory", 568, 568),
                    new SubtopicData("The Reference Range", 569, 569),
                    new SubtopicData("Validity of Laboratory Tests", 569, 569),
                    new SubtopicData("Assessment of Clinical Validity of a Laboratory Test", 570, 571),
                    new SubtopicData("Samples for analysis", 571, 571),
                    new SubtopicData("Techniques used in Clinical Chemistry", 571, 574),
                    new SubtopicData("Organ Function Tests", 574, 575),
                    new SubtopicData("Summary", 575, 575)
            )
    ),
    
    CHAPTER_49(
            "Intracellular Traffic & Sorting of Proteins",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 581, 581),
                    new SubtopicData("Many Proteins are targeted by signal sequences to their correct destinations", 581, 583),
                    new SubtopicData("The cytocolic protein sorting  branch directs proteins to the Subcellular Organelles", 583, 587),
                    new SubtopicData("Proteins sorted via the Rough ER branch have N-Terminal signal proteins", 587, 590),
                    new SubtopicData("Proteins follow several routes to be inserted into or attached to the membranes of the endoplasmic reticulum", 590, 591),
                    new SubtopicData("The ER functions as the Quality Control Compartment of the cell", 591, 592),
                    new SubtopicData("Misfolded proteins undergo Endoplasmic Reticulum-associated Degradation", 592, 593),
                    new SubtopicData("Transport vesicles are key players in intracellular protein traffic", 593, 596),
                    new SubtopicData("The assembly of membranes is complex", 596, 598),
                    new SubtopicData("Summary", 598, 598)
            )
    ),
    
    CHAPTER_50(
            "The Extracellular Matrix",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 599, 599),
                    new SubtopicData("COllagen is the most abundant protein in the animal world", 599, 599),
                    new SubtopicData("COllagens have a triple helix structure", 599, 603),
                    new SubtopicData("Elastin confers extensibility & on recoil on lung, blood vessels, & ligaments", 603, 604),
                    new SubtopicData("Fibrillins are structural componenets of Microfibrils", 604, 604),
                    new SubtopicData("Fibronectin is involved in cell adhesion & migration", 604, 605),
                    new SubtopicData("Laminin is a major protein component of Basal Laminas", 605, 606),
                    new SubtopicData("Proteoglycans & Glycosaminoglycans", 606, 612),
                    new SubtopicData("Bones ia mineralized connective tissue", 612, 614),
                    new SubtopicData("Bone is affected by many metabolic & genetic disorders", 614, 615),
                    new SubtopicData("The major components of cartilage are Type II Collagen & certain proteoglycans", 615, 616),
                    new SubtopicData("Chondrodysplasias are caused by mutations in genes encoding Type II Collagen & Fibroblast growth factor receptors", 616, 616),
                    new SubtopicData("Summary", 616, 617)
            )
    ),
    
    CHAPTER_51(
            "Muscle & the Cytoskeleton",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 618, 618),
                    new SubtopicData("Muscle is a structurally & functionally specialized tissue", 618, 620),
                    new SubtopicData("Major protein components of muscle fibers", 620, 622),
                    new SubtopicData("Muscle converts chemical energy to mechanical energy", 622, 623),
                    new SubtopicData("Contraction is orchestrated by the second messenger Ca2+", 623, 625),
                    new SubtopicData("Cardiac muscle resembles skeletal muscle in many respects", 625, 627),
                    new SubtopicData("Muscle contraction requires large quantities of ATP", 627, 628),
                    new SubtopicData("Skeletal Muscle contains slow (red) & fast (white) twitch fibers", 628, 628),
                    new SubtopicData("Muscle tissues are the target of several genetic disorders", 628, 630),
                    new SubtopicData("Skeletal Muscle constitutes the major reserve of protein in the body", 630, 630),
                    new SubtopicData("The cytoskeleton performs multiple cellular functions", 630, 632),
                    new SubtopicData("Summary", 632, 632)
            )
    ),
    
    CHAPTER_52(
            "Plasma Proteins & Immunoglobins",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 634, 635),
                    new SubtopicData("The blood has many functions", 635, 635),
                    new SubtopicData("Plasma contains a complex mixture of proteins", 635, 637),
                    new SubtopicData("Albumin the most abundant protein in human plasma", 637, 637),
                    new SubtopicData("The levels of certain plasma proteins increase during inflammation or following tissue damage", 637, 638),
                    new SubtopicData("Haptoglobin protects the kidneys", 638, 639),
                    new SubtopicData("Iron is strictly conserved", 639, 641),
                    new SubtopicData("Intracellular iron homeostasis is tightly regulated", 641, 643),
                    new SubtopicData("Iron deficiency & Anemia are common worldwide", 643, 643),
                    new SubtopicData("Serum inhibitors prevent indiscriminate proteolysis", 643, 645),
                    new SubtopicData("Deposition of plasma proteins in tissues leads to Amyloidosis", 646, 646),
                    new SubtopicData("Plasma immunoglobulins defend against invaders", 646, 650),
                    new SubtopicData("The complement system also protects against infection", 650, 651),
                    new SubtopicData("Dysfunctions of the immune system contribute to many pathologic conditions", 651, 652),
                    new SubtopicData("Summary", 652, 652)
            )
    ),
    
    CHAPTER_53(
            "Red Blood Cells",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 653, 653),
                    new SubtopicData("Red Blood Cells derive from hematopoetic stem cells", 653, 654),
                    new SubtopicData("Red Blood Cells are highly specialized", 654, 655),
                    new SubtopicData("Red Blood Cells must be continually replaced", 655, 656),
                    new SubtopicData("Oxidation of heme iron compromises oxygen transport", 656, 658),
                    new SubtopicData("The red blood cell membrane", 658, 660),
                    new SubtopicData("The biochemical basis of the ABO System", 660, 661),
                    new SubtopicData("Platelets", 661, 662),
                    new SubtopicData("Recombinant DNA Technology has had a profound impact on hematology", 662, 662),
                    new SubtopicData("Summary", 662, 662)
            )
    ),
    
    CHAPTER_54(
            "White Blood Cells",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 664, 664),
                    new SubtopicData("Defense against infection requires multiple cell types", 664, 665),
                    new SubtopicData("Multiple effectors regulate the production of white blood cells", 665, 665),
                    new SubtopicData("Leukocytes are motile", 665, 667),
                    new SubtopicData("Invading microbes & infected cells are disposed by phagocytosis", 667, 669),
                    new SubtopicData("Neytrophils & Eosinophils employ Nets to entrap parasites", 669, 669),
                    new SubtopicData("Phagocyte-derived Proteases can damage healthy cells", 669, 670),
                    new SubtopicData("Leukocytes communicate using secreted effectors", 670, 670),
                    new SubtopicData("Lymphocytes produce protective antibodies", 670, 670),
                    new SubtopicData("Summary", 671, 671)
            )
    ),
    
    CHAPTER_55(
            "Hemostasis & Thrombosis",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 677, 677),
                    new SubtopicData("Hemostasis & Throbosis have three common phases", 677, 687),
                    new SubtopicData("Summary", 687, 688)
            )
    ),
    
    CHAPTER_56(
            "Cancer: An Overview",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 689, 689),
                    new SubtopicData("Some general comments on Neoplasms", 689,690),
                    new SubtopicData("Fundamental features of Carcinogenesis", 690, 691),
                    new SubtopicData("Causes of genetic damage", 691, 691),
                    new SubtopicData("Radiation, Chemicals, & certain Viruses are major known causes of cancer", 691, 693),
                    new SubtopicData("Oncogenesis & tumor suppressor genes play key roles in causing cancer", 693, 697),
                    new SubtopicData("Growth factors & Abnormalities of their abnormalities of their receptors & signaling pathways play major roles in cancer development", 697, 697),
                    new SubtopicData("Micro-RNAs are key players in carcinogenesis & tumor metastasis", 697, 698),
                    new SubtopicData("Extracellular vesicles & Cancer", 698, 698),
                    new SubtopicData("Epigenetic mechanisms are involved in cancer", 698, 699),
                    new SubtopicData("A number of cancers display a hereditary predisposition", 699, 699),
                    new SubtopicData("Abnormalities of the cell cycle are ubiquitous in cancer cells", 699, 700),
                    new SubtopicData("Genomic instability & Aneuploidy are important characteristics of cancer cells", 700, 700),
                    new SubtopicData("Many cancer cells display elevated levels of telomerase activity", 701, 701),
                    new SubtopicData("Cancer cells have abnormalities of apoptosis that prolong their proliferative capacity", 701, 703),
                    new SubtopicData("The Tumor Microenvironment plays a critical role plays a critical role in cancer development, metastasis, & response to treatment", 703, 703),
                    new SubtopicData("Cancer cells exhibit altered metabolic programming", 703, 706),
                    new SubtopicData("Stem cells in cancer", 706, 706),
                    new SubtopicData("Tumors often stimulate Angiogenesis", 706, 706),
                    new SubtopicData("Metastasis is the most serious aspect of cancer", 706, 708),
                    new SubtopicData("There are many immunologic aspects of cancer", 708, 709),
                    new SubtopicData("Tumor Biomarkers can be measured in samples of blood & other body fluids", 709, 710),
                    new SubtopicData("Detailed genetic analyses of tumor cells is providing new insights into cancer", 710, 710),
                    new SubtopicData("Knowledge of mechanisms involved in carcinogenesis has led to the development of new therapies", 710, 712),
                    new SubtopicData("Many cancers can be prevented", 712, 712),
                    new SubtopicData("Summary", 712, 713),
                    new SubtopicData("Glossary", 714, 716)
                    
            )
    ),
    
    CHAPTER_57(
            "The Biochemistry of Aging",
            Arrays.asList(
                    
                    new SubtopicData("Biomedical Importance", 717, 717),
                    new SubtopicData("Lifespan versus Longetivity", 717, 718),
                    new SubtopicData("Aging & Mortality: Non-specific or Programmed Process?", 718, 718),
                    new SubtopicData("Wear & Tear theories of aging", 718, 720),
                    new SubtopicData("The mitochondrial theory of aging", 720, 723),
                    new SubtopicData("Molecular repair mechanisms combat wear & tear", 723, 725),
                    new SubtopicData("Aging as a Preprogrammed Process", 725, 727),
                    new SubtopicData("Why would evolution select for limited lifespan?", 727, 727),
                    new SubtopicData("Summary", 727, 728)
            )
    ),
    
    CHAPTER_58(
            "Biochemical Case Histories",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 729, 729),
                    new SubtopicData("Case 1", 729, 730),
                    new SubtopicData("Case 2", 730, 730),
                    new SubtopicData("Case 3", 731, 731),
                    new SubtopicData("Case 4", 731, 732),
                    new SubtopicData("Case 5", 732, 733),
                    new SubtopicData("Case 6", 733, 734),
                    new SubtopicData("Case 7", 734, 735),
                    new SubtopicData("Case 8", 735, 735),
                    new SubtopicData("Case 9", 735, 736)
            )
    );
    
    private final String title;
    private final List<SubtopicData> subtopics;
    

    private Content(String title, List<SubtopicData> subtopics) {
        this.title = title;
        this.subtopics = subtopics;
    }

    public String getTitle(){
        
        return this.title;
    }
    
    public List<SubtopicData> getSubtopics(){
        
        return this.subtopics;
    }
    
    public static List<Content> getContentBySection(int section){
        List<Content> content = new ArrayList<>(); // GEMINI CORRECTED ME: DO NOT USE Arrays.asList()
                                                   // the list object returned by this method would throw
                                                   // as java.lang.UnsupportedOperationException if 
                                                   // any attempt is made to modify this list.
        
        switch (section) {
            case 1 ->{
                
                content.add(Content.CHAPTER_1);
                content.add(Content.CHAPTER_2);
                content.add(Content.CHAPTER_3);
                content.add(Content.CHAPTER_4);
                content.add(Content.CHAPTER_5);
            }
                
            case 2 ->{
                
                content.add(Content.CHAPTER_6);
                content.add(Content.CHAPTER_7);
                content.add(Content.CHAPTER_8);
                content.add(Content.CHAPTER_9);
                content.add(Content.CHAPTER_10);
            }
            
            case 3 ->{
                
                content.add(Content.CHAPTER_11);
                content.add(Content.CHAPTER_12);
                content.add(Content.CHAPTER_13);
            }
            
            case 4 ->{
                
                content.add(Content.CHAPTER_14);
                content.add(Content.CHAPTER_15);
                content.add(Content.CHAPTER_16);
                content.add(Content.CHAPTER_17);
                content.add(Content.CHAPTER_18);
                content.add(Content.CHAPTER_19);
                content.add(Content.CHAPTER_20);
            }
            
            case 5 ->{
                
                content.add(Content.CHAPTER_21);
                content.add(Content.CHAPTER_22);
                content.add(Content.CHAPTER_23);
                content.add(Content.CHAPTER_24);
                content.add(Content.CHAPTER_25);
                content.add(Content.CHAPTER_26);
            }
            
            case 6 ->{
                
                content.add(Content.CHAPTER_27);
                content.add(Content.CHAPTER_28);
                content.add(Content.CHAPTER_29);
                content.add(Content.CHAPTER_30);
                content.add(Content.CHAPTER_31);
            }
            
            case 7 ->{
                
                content.add(Content.CHAPTER_32);
                content.add(Content.CHAPTER_33);
                content.add(Content.CHAPTER_34);
                content.add(Content.CHAPTER_35);
                content.add(Content.CHAPTER_36);
                content.add(Content.CHAPTER_37);
                content.add(Content.CHAPTER_38);
                content.add(Content.CHAPTER_39);
            }
            
            case 8 ->{
                
                content.add(Content.CHAPTER_40);
                content.add(Content.CHAPTER_41);
                content.add(Content.CHAPTER_42);
            }
            
            case 9 ->{
                
                content.add(Content.CHAPTER_43);
                content.add(Content.CHAPTER_44);
                content.add(Content.CHAPTER_45);
                content.add(Content.CHAPTER_46);
                content.add(Content.CHAPTER_47);
                content.add(Content.CHAPTER_48);
            }
            
            case 10 ->{
                
                content.add(Content.CHAPTER_49);
                content.add(Content.CHAPTER_50);
                content.add(Content.CHAPTER_51);
                content.add(Content.CHAPTER_52);
                content.add(Content.CHAPTER_53);
                content.add(Content.CHAPTER_54);
                            }
            
            case 11 ->{
                
                content.add(Content.CHAPTER_55);
                content.add(Content.CHAPTER_56);
                content.add(Content.CHAPTER_57);
                content.add(Content.CHAPTER_58);
            }
                        
        }
        
        return content;
        
    }
    
}

