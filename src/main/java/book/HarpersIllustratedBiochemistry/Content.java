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
                    new SubtopicData("Summary", 0, 0),
                    new SubtopicData("Appendix", 0, 0)
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
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_12(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_13(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_14(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_15(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_16(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_17(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_18(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_19(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_20(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_21(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_22(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_23(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_24(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_25(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_26(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_27(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_28(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    CHAPTER_29(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_30(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_31(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_32(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_33(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_34(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_35(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_36(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_37(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_38(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_39(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_40(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_41(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_42(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_43(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_44(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_45(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_46(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_47(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_48(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_49(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_50(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_51(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_52(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_53(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_54(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_55(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_56(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_57(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
            )
    ),
    
    CHAPTER_58(
            "",
            Arrays.asList(
                    
                    new SubtopicData("", 0, 0)
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

