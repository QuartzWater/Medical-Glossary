/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.GuytonAndHallTextBookOfMedicalPhysiology;

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
            "Functional Organization of the Human Body and Control of the \"Internal Environemt\"",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 3, 3),
                    new SubtopicData("Cells are the Living Units of the Body", 3, 3),
                    new SubtopicData("Extracellular Fluid - the \"Internal Environment\"", 3, 4),
                    new SubtopicData("Homeostasis - Maintenance of a Nearly Constant Internal Environment", 4, 5),
                    new SubtopicData("Control Systems of the Body", 5, 8),
                    new SubtopicData("Summary", 8, 8)
            )        
    ),
    
    CHAPTER_2(
            "The Cell and Its Functions",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 11, 11),
                    new SubtopicData("Organization of the Cell", 11, 12),
                    new SubtopicData("Physical Structure of the Cell", 12, 18),
                    new SubtopicData("Functional Systems of the Cell", 18, 22),
                    new SubtopicData("Intercellular Connections", 22, 23),
                    new SubtopicData("Assessing Cellular Function", 23, 25)
            )      
    ),
    
    CHAPTER_3(
            "Genetic Control of Protein Synthesis, Cell Function, and Cell Reproduction",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 27, 27),
                    new SubtopicData("Genes in the Cell Nucleus Control Protein Synthesis", 27, 29),
                    new SubtopicData("The DNA Code in the Cell Nucleus Is Transferred to RNA Code in the Cell Cytoplasm - the Process of Transcription", 29, 34),
                    new SubtopicData("Control of Gene Function and Biochemical Activity in Cells", 34, 36),
                    new SubtopicData("The DNA - Genetic System Cntrols Cell Reproduction", 36, 39),
                    new SubtopicData("Cell Differentiation", 39, 39),
                    new SubtopicData("Apoptosis", 39, 39)
            )       
    ),
    
    CHAPTER_4(
            "Transport of Substance Through Cell Membranes",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 43, 44),
                    new SubtopicData("Diffusion", 44, 50),
                    new SubtopicData("\"Active Transport\" of Substances Through Membranes", 50, 53)
            )        
    ),
    
    CHAPTER_5(
            "The Body Fluid Compartments",
            Arrays.asList(
                    new SubtopicData("Introduction", 55, 55),
                    new SubtopicData("Fluid Intake and Output Are Balanced during Steady-State Conditions", 55, 56),
                    new SubtopicData("Body Fluid Compartments", 56, 57),
                    new SubtopicData("Blood Volume", 57, 57),
                    new SubtopicData("Constituents of Extracellular and Intracellular Fluids", 57, 58),
                    new SubtopicData("Measurement of Fluid Volumes in the Different Body Fluid Compartments - the Indicator-Diffusion Principle", 58, 58),
                    new SubtopicData("Determination of Volumes of Specific Body Fluid Compartments", 58, 59)
            )        
    ),
    
    CHAPTER_6(
            "Intracellular and Extracellular Fluid Compartments and Edema",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 61, 61),
                    new SubtopicData("Basic Principles of Osmosis and Osmotic Pressure", 61, 61),
                    new SubtopicData("Osmotic Equilibrium is Maintained Between Intracellular and Extracellular Fluids", 61, 62),
                    new SubtopicData("Volume and Osmolarity of Extracellular and Intracellular Fluids in Abnormal States", 62, 63),
                    new SubtopicData("Clinical Abnormalities of  Fluid Volume Regulation: Hyponatremia and Hypernatremia", 63, 65),
                    new SubtopicData("Edema: Excess Fluid in the Tissues", 65, 67),
                    new SubtopicData("Fluids in the \"Potential Spaces\" of the Body", 67, 68)
            )        
    ),
    
    CHAPTER_7(
            "Resting Membrane Potential",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 71, 71),
                    new SubtopicData("Basic Physics of Membrane Potentials", 71, 73),
                    new SubtopicData("Resting Membrane Potential of Neurons", 73, 74),
                    new SubtopicData("Impermeant Anions (the Gibbs-Donnan Phenomenon", 74, 74)
            )        
    ),
    
    CHAPTER_8(
            "The Neuron: Stimulus and Excitability",
            Arrays.asList(
                    
                    new SubtopicData("Introduction - Structure of Neuron", 79, 79),
                    new SubtopicData("Characteristics of a Stimulus", 79, 79),
                    new SubtopicData("Excitation - the Process of Eliciting the Action Potential", 80, 81)
            )        
    ),
    
    CHAPTER_9(
            "Action Potential of the Nerve",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 83, 84),
                    new SubtopicData("Voltage-Gated Sodium and Potassium Channels", 84, 85),
                    new SubtopicData("Summary of the Events that Cause the Action Potential", 85, 85),
                    new SubtopicData("Roles of Other Ions during the Action Potential", 86, 86),
                    new SubtopicData("Local Potential", 86, 86),
                    new SubtopicData("Refractory Period", 86, 87)
            )        
    ),
    
    CHAPTER_10(
            "Propagation of the Nerve Impulse",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 89, 90),
                    new SubtopicData("Special Characteristics of Signal Transmission in Nerve Trunks", 90, 92)
            )        
    ),
    
    CHAPTER_11(
            "Peripheral Nerve Damage",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 93, 93),
                    new SubtopicData("Wallerian Degeneration", 93, 94),
                    new SubtopicData("Functional Assessment of Nerve Damage Using the Strength - Duration Curve", 94, 94),
                    new SubtopicData("Nerve Regeneration", 94, 95)
            )        
    ),
    
    CHAPTER_12(
            "Neuromuscular Transmission",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 97, 97),
                    new SubtopicData("Secretion of Acetylcholine by the Nerve Terminals", 97, 100),
                    new SubtopicData("Molecular Biology of Acetylcholine Formation and Release", 100, 100),
                    new SubtopicData("Drugs/Toxins that Enhance or Block Transmission at the Neuromuscular Junction", 100, 101),
                    new SubtopicData("Myasthenia Gravis", 101, 101),
                    new SubtopicData("Lambert-Eaton Syndrome", 101, 101),
                    new SubtopicData("Muscle Action Potential - Comparison with nerve Action Potential", 101, 102)
                    
            )        
    ),
    
    CHAPTER_13(
            "Excitation-Contraction Coupling",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 105, 105),
                    new SubtopicData("Transverse Tubule-Sarcoplasmic Reticulum System", 105, 105),
                    new SubtopicData("Release of Calcium Ions by the Sarcoplasmic Reticulum", 105, 108)
            )        
    ),
    
    CHAPTER_14(
            "Molecular Basis of Skeletal Muscle Contraction",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 111, 111),
                    new SubtopicData("Physiological Anatomy of Skeletal Muscle", 111, 114),
                    new SubtopicData("General Mechanism of Muscle Contraction", 114, 114),
                    new SubtopicData("Molecular Mechanism of Muscle Contraction", 114, 117)
            )        
    ),
    
    CHAPTER_15(
            "Chemical Changes During Skeletal Muscle Contraction",
            Arrays.asList(
                    
                    new SubtopicData("Introduction - Energetics of Muscle Contraction", 119,121)
            )        
    ),
    
    CHAPTER_16(
            "Characteristics of Skeletal Muscles Contraction",
            Arrays.asList(
                    
                    new SubtopicData("The Amount of Actin and Myosin Filament Overlap Determines Tension Developed by the Contracting Muscle", 123, 124),
                    new SubtopicData("Relation of Velocity of Contraction to Load", 124, 125),
                    new SubtopicData("Mechanics of Skeletal Muscle Contraction", 125, 127)
            )        
    ),
    
    CHAPTER_17(
            "Applied Skeletal Muscle Physiology",
            Arrays.asList(
                    
                    new SubtopicData("Blood Flow Regulation in Skeletal Muscle at Rest and During Exercise", 129, 130),
                    new SubtopicData("Muscles in Exercise", 130, 134),
                    new SubtopicData("Gender Differences in Athletic Performance", 134, 134),
                    new SubtopicData("Drugs and Athletes", 134, 135)
            )        
    ),
    
    CHAPTER_18(
            "Introduction to Blood and Plasma Proteins",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 139, 139),
                    new SubtopicData("Blood Volume", 139, 139),
                    new SubtopicData("Functional Roles of the Plasma Proteins", 140, 141),
                    new SubtopicData("Separation of Plasma Proteins", 141, 141),
                    new SubtopicData("Plasmapheresis", 141, 141)
            )        
    ),
    
    CHAPTER_19(
            "Red Blood Cells (Erythrocytes)",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 143, 143),
                    new SubtopicData("Shape and Size of Red Blood Cells", 143, 143),
                    new SubtopicData("Concentration of Red Blood Cells in the Blood", 143, 143),
                    new SubtopicData("Quantity of Haemoglobin in the Cells", 143, 144),
                    new SubtopicData("Life Span of Red Blood Cells is About 120 Days", 144, 144),
                    new SubtopicData("Erythrocyte Sedimentation Rate", 144, 144)

            )        
    ),
    
    CHAPTER_20(
            "Erythropoiesis",
            Arrays.asList(
                    
                    new SubtopicData("Areas of Body That Produces Red Blood Cells", 147, 147),
                    new SubtopicData("Bone Marrow", 147, 147),
                    new SubtopicData("Genesis of Blood Cells", 147, 148),
                    new SubtopicData("Stages of Differentiation of Red Blood Cells", 148, 148),
                    new SubtopicData("Erythropoietin Regulates Red Blood Cell Production", 148, 150),
                    new SubtopicData("Maturation of Red Blood Cells - Requirement for Vitamin B12 (Cyanocobalamin) and Folic Acid", 150, 150)
            )        
    ),
    
    CHAPTER_21(
            "Haemoglobin",
            Arrays.asList(
                    
                    new SubtopicData("Formation of Haemoglobin", 153, 154),
                    new SubtopicData("Iron Metabolism", 154, 155)
            )        
    ),
    
    CHAPTER_22(
            "Anemia and Polycythemia",
            Arrays.asList(
                    
                    new SubtopicData("Anemia", 157, 158),
                    new SubtopicData("RBC Indices in Anemia", 158, 159),
                    new SubtopicData("Polycythemia", 159, 159)
            )        
    ),
    
    CHAPTER_23(
            "Jaundice",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 161, 161),
                    new SubtopicData("Hemolytic Jaundice is Caused by Hemolysis of Red Blood Cells", 161, 161),
                    new SubtopicData("Obstructive Jaundice is Caused by Obstruction of Bile ducts or Liver Disease", 161, 161),
                    new SubtopicData("Diagnostic Differences Between Hemolytic and Obstructive Jaundice", 161, 162)
            )        
    ),
    
    CHAPTER_24(
            "White Blood Cells",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 165, 165),
                    new SubtopicData("Leukocytes (White Blood Cells)", 165, 167),
                    new SubtopicData("Neutrophils and Macrophages Defend Against Infections", 167, 168),
                    new SubtopicData("Monocyte-Macrophage Cell System (Reticuloendothelial System)", 169, 170),
                    new SubtopicData("Eosinophils", 170, 170),
                    new SubtopicData("Basophils", 170, 171),
                    new SubtopicData("Leukopenia", 171, 171),
                    new SubtopicData("Leukemias", 171, 171)
            )        
    ),
    
    CHAPTER_25(
            "Immunity and Allergy",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 173, 173),
                    new SubtopicData("Acquired (adaptive) Immunity", 173, 182),
                    new SubtopicData("Allergy and Hypersensitivity", 182, 183)
            )        
    ),
    
    CHAPTER_26(
            "Platelets",
            Arrays.asList(
                    
                    new SubtopicData("Thrombopoiesis", 185, 185),
                    new SubtopicData("Hemostasis Events", 185, 185),
                    new SubtopicData("Vascular Constriction", 185, 187),
                    new SubtopicData("Thrombocytopenia", 187, 187),
                    new SubtopicData("Thromboembolic Conditions", 187, 188),
                    new SubtopicData("Bleeding Time", 188, 188)
            )        
    ),
    
    CHAPTER_27(
            "Blood Coagulation",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 189, 189),
                    new SubtopicData("Conversion of Prothrombin to Thrombin", 189, 190),
                    new SubtopicData("Conversion of Fibrinogen to Fibrin - Formation of the Clot", 190, 190),
                    new SubtopicData("Positive Feedback of CLot Formation", 190, 190),
                    new SubtopicData("Initiation of Coagulation: Formation of Prothrombin Activator", 190, 192),
                    new SubtopicData("Intravascular Anticoagulants Prevent Blood Clotting in the Normal Vascular System", 193, 193),
                    new SubtopicData("Plasmin Causes Lysis of Blood Clots", 193, 193),
                    new SubtopicData("Conditions That Cause Excessive Bleeding in Humans", 193, 195),
                    new SubtopicData("Anticoagulants for Clinical Use", 195, 196),
                    new SubtopicData("Blood Coagulation Tests", 196, 197)
            )        
    ),
    
    CHAPTER_28(
            "Blood Groups",
            Arrays.asList(
                    
                    new SubtopicData("Multiplicity of Antigens in the Blood Cells", 199, 199),
                    new SubtopicData("O-A-B Blood Types", 199, 201),
                    new SubtopicData("Rh Blood Types", 201, 203)
            )        
    ),
    
    CHAPTER_29(
            "Organization of the Cardiovascular System",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 207, 207),
                    new SubtopicData("Physical Characteristics of the Circulation", 207, 209),
                    new SubtopicData("Basic Principles of Circulatory Function", 209, 210)
            )        
    ),
    
    CHAPTER_30(
            "Properties of Cardiac Muscle",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 211, 211),
                    new SubtopicData("Anatomical Characteristics of Cardiac Muscle", 211, 211),
                    new SubtopicData("Physiological Characteristics of Cardiac Muscle", 211, 215)
            )        
    ),
    
    CHAPTER_31(
            "Cardiac Action Potentials",
            Arrays.asList(
                    
                    new SubtopicData("Membrane Potentials for the SA Node and Muscle Fibers", 217, 219),
                    new SubtopicData("Control of Cardiac Potentials by the Sympathetic and Parasympathetic Nerves", 219, 220)
            )       
    ),
    
    CHAPTER_32(
            "Origin and Conduction of the Cardiac Impulse",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 223, 223),
                    new SubtopicData("Specialized Excitatory and Conductory System of the Heart", 223, 225),
                    new SubtopicData("Control of Excitation and Conduction in the Heart", 225, 227)
            )        
    ),
    
    CHAPTER_33(
            "The Normal Electrocardiogram",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 229, 229),
                    new SubtopicData("Characteristics of the Normal Electrocardiogram", 229, 231),
                    new SubtopicData("Flow of Current Around the Heart During the Cardiac Cycle", 231, 232),
                    new SubtopicData("Electrocardiographic Leads", 232, 234)
            )        
    ),
    
    CHAPTER_34(
            "Clinical Applications of the Electrocardiogram",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 237, 237),
                    new SubtopicData("Abnormal Sinus Rhythm", 237, 238),
                    new SubtopicData("Abnormal Rhythm That Results From Block of Heart Signals Within the Intracellular Conduction Pathways", 238, 240),
                    new SubtopicData("Premature Contractions", 240, 241),
                    new SubtopicData("Paroxysmal Tachycardia", 241, 241),
                    new SubtopicData("ventricular Fibrillation", 241, 243),
                    new SubtopicData("Atrial Fibrillation", 243, 244),
                    new SubtopicData("Atrial Flutter", 244, 244),
                    new SubtopicData("Vectorial Analysis of the ECG and its Application to ventricular Hypertrophy", 244, 245),
                    new SubtopicData("Vectorial Analysis of the Normal Electrocardiogram", 245, 247),
                    new SubtopicData("Mean Electrical Axis of the Ventricular QRS - and Its Significance", 247, 248),
                    new SubtopicData("Coronary Ischemia", 248, 250)
            )       
    ),
    
    CHAPTER_35(
            "Cardiac Cycle",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 253, 253),
                    new SubtopicData("Diastole adn Systole", 253, 258),
                    new SubtopicData("Oxygen Utilization by the Heart", 258, 260),
                    new SubtopicData("Efficiency of Cardiac Contraction", 260, 260)
            )        
    ),
    
    CHAPTER_36(
            "Cardiac Output and Venous Return",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 263, 263),
                    new SubtopicData("Normal Values for Cardiac Output at Rest and During Activity", 263, 263),
                    new SubtopicData("Control of Cardiac Output by Venous Return - the Frank-Starling Mechanism of the Heart", 263, 266),
                    new SubtopicData("venous Returns Curves", 266, 268),
                    new SubtopicData("Analysis of Cardiac Output and Right Atrial Pressure Using Simultaneous Cardiac Output and Venous Return Curves", 269, 270),
                    new SubtopicData("Methods for Measuring Cardiac Output", 270, 271)
            )        
    ),
    
    CHAPTER_37(
            "Regulation of Cardiac Output",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 273, 273),
                    new SubtopicData("Intrinsic Regulation of Heart Pumping - the Franklin-Starling Mechanism", 273, 276),
                    new SubtopicData("Effect of Temperature on Heart Function", 276, 276),
                    new SubtopicData("Increasing the Arterial Pressure Load (Up to a Limit) Does Not Decrease the Cardiac Output", 276, 276)
            )        
    ),
    
    CHAPTER_38(
            "Hemodynamics",
            Arrays.asList(
                    
                    new SubtopicData("Interrelationships of Pressure, Flow and Resistance", 279, 284)
            )        
    ),
    
    CHAPTER_39(
            "Microcirculation",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 287, 287),
                    new SubtopicData("Structure of the Microcirculation", 287, 288),
                    new SubtopicData("Flow of Blood in the Capillaries - Vasomotion", 288, 289),
                    new SubtopicData("Exchange of Water, Nutrients, and Other Substances Between the Blood and Interstitial Fluid", 289, 290),
                    new SubtopicData("Interstitium and Interstitial Fluid", 290, 290),
                    new SubtopicData("Fluid Filtration Across Capillaries", 290, 293)
            )        
    ),
    
    CHAPTER_40(
            "The Lymphatic System",
            Arrays.asList(
                    
                    new SubtopicData("Formation of Lymph", 295, 295),
                    new SubtopicData("Rate of Lymph Flow", 295, 296),
                    new SubtopicData("The Lymphatic System Plays a Key Role in Controlling Interstitial Fluid Protein Concentration, Volume, and Pressure", 296, 297)
            )        
    ),
    
    CHAPTER_41(
            "The Venous System",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 299, 299),
                    new SubtopicData("Right Atrial Pressure (Central Venous Pressure) and its Regulation", 299, 300),
                    new SubtopicData("Peripheral Venous Pressure and Its Determination", 300, 302),
                    new SubtopicData("Blood Reservoir Function of the Veins", 302, 303)
            )        
    ),
    
    CHAPTER_42(
            "Determinants of Arterial Blood Pressure",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 305, 305),
                    new SubtopicData("Arterial Pressure Pulsations", 305, 307),
                    new SubtopicData("Vascular Distensibility", 307, 308),
                    new SubtopicData("Clinical Methods for Measuring Systolic and Diastolic Pressures", 308, 310)
            )        
    ),
    
    CHAPTER_43(
            "Short Term Regulation of Arterial Blood Pressure",
            Arrays.asList(
                    
                    new SubtopicData("Autonomic Nervous System", 313, 315),
                    new SubtopicData("Role of the Nervous System in Rapid Control of Arterial Pressure", 315, 320),
                    new SubtopicData("Special Features of Nervous Control of Arterial Pressure", 320, 321)
            )        
    ),
    
    CHAPTER_44(
            "Long Term Regulation of Arterial Blood Pressure",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 323, 323),
                    new SubtopicData("Quantification of Pressure Diuresis as a Basis for Arterial Pressure Control", 323, 323),
                    new SubtopicData("Two Key Determinants of Long-Term Arterial Pressure", 323, 324),
                    new SubtopicData("The Renin-Angiotensin System: Its Role in Arterial Pressure Control", 325, 328)
            )        
    ),
    
    CHAPTER_45(
            "Local and Humoral Control of Blood Flow",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 329, 329),
                    new SubtopicData("Variations in Blood Flow in Different Tissues and Organs", 329, 329),
                    new SubtopicData("Mechanisms of Blood Flow Control", 330, 334),
                    new SubtopicData("Humoral Control", 334, 335)
            )        
    ),
    
    CHAPTER_46(
            "Coronary Circulation",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 337, 337),
                    new SubtopicData("Physiological Anatomy of the Coronary Blood Supply", 337, 337),
                    new SubtopicData("Normal Coronary Blood Flow - Averages 5% of Cardiac Output", 337, 338),
                    new SubtopicData("Control of Coronary Blood Flow", 338, 339),
                    new SubtopicData("Special Features of Cardiac Muscle Metabolism", 339, 339),
                    new SubtopicData("Ischemic Heart Disease", 339, 340),
                    new SubtopicData("Stages of Recovery from Acute Myocardial Infarction", 340, 341),
                    new SubtopicData("Function of the Heart after Recovery from Myocardial Infarction", 341, 341),
                    new SubtopicData("Pain in Coronary Heart Disease", 341, 341),
                    new SubtopicData("Surgical Treatment of Coronary Artery Disease", 341, 342)
            )        
    ),
    
    CHAPTER_47(
            "Cerebral Circulation",
            Arrays.asList(
                    
                    new SubtopicData("Anatomy of Cerebral Blood Flow", 345, 345),
                    new SubtopicData("Regulation of Cerebral Blood Flow", 345, 348),
                    new SubtopicData("Cerebral Microcirculation", 348, 348)
            )        
    ),
    
    CHAPTER_48(
            "Splanchnic Circulation",
            Arrays.asList(
                    
                    new SubtopicData("Hepatic Blood Flow", 351, 351),
                    new SubtopicData("Anatomy of the Gastrointestinal Blood Supply", 351, 353),
                    new SubtopicData("Effect of Gut Activity and Metabolic Factors on Gastrointestinal Blood Flow", 353, 353),
                    new SubtopicData("Regulation of the Splanchnic Circulation a Synthesis", 353, 354),
                    new SubtopicData("Nervous Control", 354, 354)
            )        
    ),
    
    CHAPTER_49(
            "Fetal and Neonatal Circulation",
            Arrays.asList(
                    
                    new SubtopicData("Circulatory Readjustments at Birth", 357, 359),
                    new SubtopicData("Special Functional Problems in the Circulation of the Neonate", 359, 361)
            )        
    ),
    
    CHAPTER_50(
            "Valvular Heart Disease",
            Arrays.asList(
                    
                    new SubtopicData("Causes of Heart Sounds", 363, 366),
                    new SubtopicData("Abnormal Circulatory Dynamics in Valvular Heart Disease", 366, 367),
                    new SubtopicData("Hypertrophy of the Heart in Valvular Heart Disease", 367, 367)
            )        
    ),
    
    CHAPTER_51(
            "Cardiac Failure",
            Arrays.asList(
                    
                    new SubtopicData("Circulatory Dynamics in Cardiac Failure", 369, 372),
                    new SubtopicData("Unilateral Left Heart Failure", 372, 373),
                    new SubtopicData("Low-Output Cardiac Failure - Cardiogenic Shock", 373, 373),
                    new SubtopicData("Edema in Patients with Cardiac Failure", 373, 374),
                    new SubtopicData("Cardiac Reserve", 374, 374)
            )        
    ),
    
    CHAPTER_52(
            "Circulatory Shock",
            Arrays.asList(
                    
                    new SubtopicData("Causes of Shock", 377, 379),
                    new SubtopicData("Stages of Shock", 379, 382),
                    new SubtopicData("Other Types of Shock", 382, 383),
                    new SubtopicData("Circulatory Arrest", 384, 385)
            )        
    ),
    
    CHAPTER_53(
            "Organization of the Respiratory System",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 389, 389),
                    new SubtopicData("Anatomical Organization of the Lungs and Airways", 389, 391),
                    new SubtopicData("Physical Laws Applicable in Respiratory Physiology", 391, 392),
                    new SubtopicData("Non-Respiratory Functions of the Lungs", 392, 395)
            )        
    ),
    
    CHAPTER_54(
            "Mechanism of Breathing",
            Arrays.asList(
                    
                    new SubtopicData("Mechanics of Pulmonary Ventilation", 397, 403),
                    new SubtopicData("Minute Respiratory Volume", 403, 403),
                    new SubtopicData("Alveolar Ventilation", 403, 404)
            )        
    ),
    
    CHAPTER_55(
            "Lung Volumes and Capacities",
            Arrays.asList(
                    
                    new SubtopicData("Lung Function Tests", 407, 407),
                    new SubtopicData("Pulmonary Volumes and Capacities", 407, 408),
                    new SubtopicData("Determination of Functional Residual Capacity, Residual Volume, and Total Lung Capacities", 408, 411),
                    new SubtopicData("Mucus Lining the Respiratory Passageways, and Cilia Action to Clear the Passageways", 411, 411),
                    new SubtopicData("FLow-Volume Curves", 411, 414)
            )        
    ),
    
    CHAPTER_56(
            "Ventilation",
            Arrays.asList(
                    
                    new SubtopicData("Minute Respiratory Volume (Minute Ventilation)", 417, 417),
                    new SubtopicData("Alveolar Ventilation", 417, 417),
                    new SubtopicData("Maximum Voluntary Ventilation", 417, 418),
                    new SubtopicData("Breathing Reserve", 419, 419),
                    new SubtopicData("Gas Pressures in a Mixture of Gases - \"Partial Pressures\" of Individual Gases", 418, 418),
                    new SubtopicData("Pressures of Gases Dissolved in Water and Tissues", 418, 419),
                    new SubtopicData("Relationship Between Alveolar Ventilation and Partial Pressures of Oxygen and Carbon Dioxide", 419, 420),
                    new SubtopicData("Causes of Hypoventilation and Hyperventilation", 420, 421)
            )        
    ),
    
    CHAPTER_57(
            "Pulmonary Circulation",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 423, 423),
                    new SubtopicData("Physiological Anatomy of the Pulmonary Circulatory System", 423, 423),
                    new SubtopicData("Pressures in the Pulmonary System", 423, 425),
                    new SubtopicData("Pulmonary Vascular Resistance", 425, 425),
                    new SubtopicData("Blood Volume of the Lungs", 426, 426),
                    new SubtopicData("Blood Flow Through the Lungs and its Distribution", 426, 426),
                    new SubtopicData("Effect of Hydrostatic Pressure Gradients in the Lungs on Regional Pulmonary Blood Flow", 427, 429),
                    new SubtopicData("Pulmonary Capillary Dynamics", 429, 430),
                    new SubtopicData("Fluid in the Pleural Cavity", 430, 432)
            )        
    ),

    CHAPTER_58(
            "Diffusion of Gases",
            Arrays.asList(

                    new SubtopicData("Physics of Gas Diffusion and Gas Partial Pressure", 435, 437),
                    new SubtopicData("Diffusion of Gases Through the Respiratory Membrane", 437, 437),
                    new SubtopicData("Diffusion and Perfusion Limitations of Gas Transfer", 437, 440)
            )
    ),

    CHAPTER_59(
            "Oxygen Transport",
            Arrays.asList(
                    
                    new SubtopicData("Composition of Alveolar Air and Atmospheric Air are Different", 443, 443),
                    new SubtopicData("Methods of Oxygen Transport", 443, 446),
                    new SubtopicData("Hypoxia and Oxygen Therapy", 446, 447)
            )        
    ),
    
    CHAPTER_60(
            "Carbon Dioxide Transport",
            Arrays.asList(
                    
                    new SubtopicData("Transport of CO2 in the Blood", 449, 452),
                    new SubtopicData("Respiratory Exchange Ratio", 452, 453)
            )       
    ),
    
    CHAPTER_61(
            "Chemical Regulation of Respiration",
            Arrays.asList(
                    
                    new SubtopicData("Chemical Control of Respiration", 455, 456),
                    new SubtopicData("Peripheral Chemoreceptors System - Role of Oxygen in Respiratory Control", 456, 458),
                    new SubtopicData("Regulation of Respiration During Exercise", 458, 461)
            )     
    ),

    CHAPTER_62(
            "Neural Regulation of Respiration",
            Arrays.asList(
                    
                    new SubtopicData("Respiratory Centre", 463, 465),
                    new SubtopicData("Other Factors that Affect Respiration", 465, 468)
            )  
    ),
    
    CHAPTER_63(
            "Respiration in Unusual Environments",
            Arrays.asList(
                    
                    new SubtopicData("Effects of Low Oxygen Pressure on the Body", 469, 473),
                    new SubtopicData("Aviation and Space Physiology", 473, 476),
                    new SubtopicData("Physiology of Deep-Sea Diving and Other Hyperbaric Conditions", 476, 476),
                    new SubtopicData("Changes that Occur with Deep-Sea Diving", 476, 479),
                    new SubtopicData("Self-Contained Underwater Breathing Apparatus", 479, 480)
            )       
    ),
    
    CHAPTER_64(
            "Applied Respiratory Physiology",
            Arrays.asList(
                    
                    new SubtopicData("Respiratory Disorders", 483, 487),
                    new SubtopicData("Hypercapnia - Excess Carbon Dioxide in the Body Fluids", 487, 489),
                    new SubtopicData("Artificial Respiration", 490, 490),
                    new SubtopicData("Oxygen Therapy", 490, 490),
                    new SubtopicData("Hyperbaric Oxygen Therapy", 490, 491)
            )    
    ),
    
    CHAPTER_65(
            "Organization of the Gastrointestinal System",
            Arrays.asList(
                    
                    new SubtopicData("General Principles of Gastrointestinal Motility", 495, 495),
                    new SubtopicData("Types of Smooth Muscle", 495, 498),
                    new SubtopicData("Contractile Mechanism in Smooth Muscle", 498, 502),
                    new SubtopicData("Neural Control of Gastrointestinal Function - Enteric Nervous System", 502, 504),
                    new SubtopicData("Gastrointestinal Blood Flow - \"Splanchnic Circulation\"", 504, 504),
                    new SubtopicData("Gut-Brain Axis", 504, 505)
            )      
    ),
    
    CHAPTER_66(
            "Salivary Glands and Secretion",
            Arrays.asList(
                    
                    new SubtopicData("General Principles of Alimentary Tract Secretion", 507, 509),
                    new SubtopicData("Secretion of Saliva", 509, 513)
            )    
    ),
    
    CHAPTER_67(
            "Gastric Secretions",
            Arrays.asList(
                    
                    new SubtopicData("Esophageal Secretion", 515, 515),
                    new SubtopicData("Gastric Secretion", 515, 521),
                    new SubtopicData("Pathophysiology and Treatment Modalities for Acid Peptic Disease", 521, 522),
                    new SubtopicData("Gastric Function Tests", 522, 523)
            )     
    ),
    
    CHAPTER_68(
            "Exocrine Pancrease",
            Arrays.asList(
                    
                    new SubtopicData("Pancreatic Secretion", 525, 529)
            )       
    ),
    
    CHAPTER_69(
            "Functions of the Liver",
            Arrays.asList(
                    
                    new SubtopicData("Physiology Anatomy of the Liver", 531, 532),
                    new SubtopicData("Hepatic Vascular and Lymph Systems", 532, 532),
                    new SubtopicData("Metabolic Functions of the Liver", 532, 533),
                    new SubtopicData("Bile Secretions by the Liver", 533, 536),
                    new SubtopicData("Measurement of Bilirubin in the Bile as a Clinical Diagnostic Tool", 536, 537),
                    new SubtopicData("Liver Function Tests", 537, 538)
            )     
    ),
    
    CHAPTER_70(
            "Digestion and Absorption of Carbohydrates",
            Arrays.asList(
                    
                    new SubtopicData("General Principles of Digestion", 541, 541),
                    new SubtopicData("Digestion of the Various Foods by Hydrolysis", 541, 542),
                    new SubtopicData("Basic Principle of Gastrointestinal Absorption", 542, 544),
                    new SubtopicData("Absorption in the Small Intestine", 544, 547)
            )      
    ),
    
    CHAPTER_71(
            "Digestion and Absorption of Proteins",
            Arrays.asList(
                    
                    new SubtopicData("Hydrolysis of Proteins", 549, 551),
                    new SubtopicData("Basic Principles of Gastrointestinal Absorption", 551, 551)
            )   
    ),
    
    CHAPTER_72(
            "Digestion and Absorption of Fats",
            Arrays.asList(
                    
                    new SubtopicData("Digestion of the Various Foods by Hydrolysis", 553, 555),
                    new SubtopicData("Absorption in the Small Intestine", 555, 555)
            )    
    ),
    
    CHAPTER_73(
            "Functions of the Small and Large Intestine",
            Arrays.asList(
                    
                    new SubtopicData("Secretions of the Small Intestine", 557, 558),
                    new SubtopicData("Secretion of Mucus by the Large Intestine", 558, 558),
                    new SubtopicData("Absorption in the Large Intestine: Formation of Feces", 558, 560)
            )    
    ),
    
    CHAPTER_74(
            "Gastrointestinal Motility",
            Arrays.asList(
                    
                    new SubtopicData("Functional Movements in the Gastrointestinal Tract", 563, 564),
                    new SubtopicData("Ingestion of Food", 564, 566),
                    new SubtopicData("Motor Function of the Stomach", 566, 568),
                    new SubtopicData("Migrating Motor Complex", 568, 568),
                    new SubtopicData("Movements of the Small Intestine", 568, 570),
                    new SubtopicData("Movements of the Colon", 570, 572),
                    new SubtopicData("Other Automatic Reflexes that Affect Bowel Activity", 572, 572)
            )      
    ),
    
    CHAPTER_75(
            "Physiology of Gastrointestinal Disease",
            Arrays.asList(
                    
                    new SubtopicData("Disorders of Swallowing and the Esophagus", 575, 575),
                    new SubtopicData("Disorders of the Stomach", 575, 576),
                    new SubtopicData("Disorders of the Small Intestine", 576, 577),
                    new SubtopicData("Disorders of the Large Intestine", 577, 579),
                    new SubtopicData("General Disorders of the Gastrointestinal Tract", 579, 580)
            )
    ),
    
    CHAPTER_76(
            "Functional Anatomy of the Kidney",
            Arrays.asList(
                    
                    new SubtopicData("Multiple Functions of the Kidney", 585, 586),
                    new SubtopicData("Physiological Anatomy of the Kidney", 586, 588)
            )
    ),
    
    CHAPTER_77(
            "Urine Formation by the Kidneys: Renal Blood Flow, Glomerular Filtration, and their Control",
            Arrays.asList(
                    
                    new SubtopicData("General Principles: Urine Formation Results from Glomerular Filtration, Tubular Reabsorption, and Tubular Secretion", 591, 592),
                    new SubtopicData("Renal Blood Flow", 592, 594),
                    new SubtopicData("Autoregulation of Glomerular Filtration Rate and Renal Blood Flow", 594, 595),
                    new SubtopicData("Glomerular Filtration - The First Step in Urine Formation", 595, 596),
                    new SubtopicData("Use of Clearance Methods to Measure Glomerular Filtration Rate and Renal Blood Flow", 596, 599),
                    new SubtopicData("Determinants of the Glomerular Filtration Rate Box 77.3", 599, 601),
                    new SubtopicData("Physiological Control of Glomerular Filtration and Renal Blood Flow", 601, 604)

            ) 
    ),
    
    CHAPTER_78(
            "Tubular Function",
            Arrays.asList(
                    
                    new SubtopicData("Renal Tubular Reabsorption and Secretion", 607, 607),
                    new SubtopicData("Tubular Reabsorption Includes Passive and Active Mechanisms", 607, 612)
            )     
    ),
    
    CHAPTER_79(
            "Concentration and Dilution of Urine",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 615, 615),
                    new SubtopicData("Antidiuretic Hormone Controls Urine Concentration", 615, 616),
                    new SubtopicData("Kidneys Conserve Water by Excreting Concentrated Urine", 616, 622),
                    new SubtopicData("Quantifying Renal Urine Concentration and DilutionL \"Free Water\" and Osmolar Clearances", 622, 623)
            )
    ),
    
    CHAPTER_80(
            "Control of Extracellular Fluid Osmolarity and Sodium Concentration",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 625, 625),
                    new SubtopicData("Osmoreceptor-Antidiuretic Hormone Feedback System", 625, 627),
                    new SubtopicData("Importance of Thirst in Controlling Extracellular Fluid Osmolarity and Sodium Concentration", 627, 630),
                    new SubtopicData("Salt-Appetite Mechanism for Controlling Extracellular Fluid Sodium Concentration and Volume", 630, 630)
            )    
    ),
    
    CHAPTER_81(
            "Renal Regulation of Potassium, Calcium, Phosphate, and Magnesium",
            Arrays.asList(
                    
                    new SubtopicData("Regulation of Extracellular Fluid Potassium Concentration and Potassium Excretion", 633, 637),
                    new SubtopicData("Control of Renal Calcium Excretion and Extracellular Calcium Ion Concentration", 637, 639),
                    new SubtopicData("Control of Renal Magnesium Excretion and Extracellular Magnesium Ion Concentration", 639, 640)
            )     
    ),
    
    CHAPTER_82(
            "Acid-Base Regulation",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 641, 641),
                    new SubtopicData("H+ Concentration is Precisely Regulated", 641, 641),
                    new SubtopicData("Acids and Bases - Their Definitions and Meanings", 641, 642),
                    new SubtopicData("Defending Against Changes in H+ Concentration: Buffers, Lungs, and Kidneys", 642, 642),
                    new SubtopicData("Buffering of H+ in the Body Fluids Box 82.1", 642, 643),
                    new SubtopicData("Bicarbonate Buffer System", 643, 645),
                    new SubtopicData("Phosphate Buffer System", 645, 645),
                    new SubtopicData("Proteins Are Important Intracellular Buffers", 645, 645),
                    new SubtopicData("Respiratory Regulation of Acid-Base Balance Box 82.2", 645, 647),
                    new SubtopicData("Renal Control of Acid-Base Balance Box 82.2", 647, 647),
                    new SubtopicData("Secretion of H+ and Reabsorption of HCO3- by the Renal Tubules", 647, 649),
                    new SubtopicData("Combination of Excess H+ with Phosphate and Ammonia Buffers in the Tubule Generates \"New\" HCO3-", 649, 650),
                    new SubtopicData("Renal Correction of Acidosis - Increased Excretion of H+ and Addition of HCO3- to the Extracellular Fluid", 651, 651),
                    new SubtopicData("Renal Correction of Alkalosis - Decreased Tubular Secretion of H+ and Increased Excretion of HCO3-", 651, 652),
                    new SubtopicData("Clinical Causes of Acid-Base Disorders", 652, 653),
                    new SubtopicData("Treatment of Acidosis or Alkalosis", 653, 653),
                    new SubtopicData("Clinical Measurements and Analysis if Acid-Base Disorders", 652, 655)
            )      
    ),
    
    CHAPTER_83(
            "Micturition",
            Arrays.asList(
                    
                    new SubtopicData("Physiological Anatomy of the Bladder", 657, 658),
                    new SubtopicData("Transport of Urine from the Kidney Through the Ureters and into the Bladder", 658, 659),
                    new SubtopicData("Filling of the Bladder and Bladder Wall Tone: the Cystometrogram", 659, 659),
                    new SubtopicData("Micturition Reflex", 659, 660),
                    new SubtopicData("Abnormalities of Micturition", 660, 660)
            )      
    ),
    
    CHAPTER_84(
            "Applied Physiology of the Renal System",
            Arrays.asList(
                    
                    new SubtopicData("Renal Function Tests", 663, 664),
                    new SubtopicData("Concentration and Dilution Tests of Renal Function", 664, 664),
                    new SubtopicData("Diuretics and Their Mechanisms of Action", 664, 666),
                    new SubtopicData("Kidney Diseases", 666, 666),
                    new SubtopicData("Acute Kidney Injury", 666, 666),
                    new SubtopicData("Chronic Kidney Disease Is Often Associated with Irreversible Loss of Functional Nephrons", 666, 669),
                    new SubtopicData("Specific Tubular Disorders", 669, 669),
                    new SubtopicData("Treatment of Renal Failure by Transplantation or by Dialysis with an Artificial Kidney", 669, 671)
            )        
    ),
    
    CHAPTER_85(
            "Organization of Endocrine System",
            Arrays.asList(
                    
                    new SubtopicData("Definition of a Hormone", 675, 675),
                    new SubtopicData("Coordination of Body Functions by Chemical Messengers", 675, 678),
                    new SubtopicData("Feedback Control of Hormone Secretion", 678, 680)
            )    
    ),
    
    CHAPTER_86(
            "Hormone-Receptor Interactions",
            Arrays.asList(
                    
                    new SubtopicData("Chemical Structure and Synthesis of Hormones", 683, 684),
                    new SubtopicData("Hormone Secretion, Transport, and Clearance from the Blood", 684, 685),
                    new SubtopicData("Mechanisms of Hormone Action", 685, 691),
                    new SubtopicData("Measurement of Hormone Concentration in the Blood", 691, 692)
            )
    ),
    
    CHAPTER_87(
            "Anterior Pituitary Gland and Hypothalamus",
            Arrays.asList(
                    
                    new SubtopicData("Pituitary Gland and Its Relation to the Hypothalamus", 695, 696),
                    new SubtopicData("Hypothalamus Controls Pituitary", 696, 697),
                    new SubtopicData("Physiological Functions of Growth Hormone", 698, 702)
            )      
    ),
    
    CHAPTER_88(
            "Posterior Pituitary Gland",
            Arrays.asList(
                    
                    new SubtopicData("Posterior Pituitary Hormones Are Synthesized by Cell Bodies in the Hypothalamus", 705, 705),
                    new SubtopicData("Posterior Pituitary Gland and Its Relation to the Hypothalamus", 705, 708),
                    new SubtopicData("Disorders of Posterior Pituitary Hormone Secretions", 708, 710)
            )     
    ),
    
    CHAPTER_89(
            "Thyroid Gland, Thymus, and Pineal Gland",
            Arrays.asList(
                    
                    new SubtopicData("Synthesis and Secretion of the Thyroid Metabolic Hormones", 711, 714),
                    new SubtopicData("Metabolism and Excretion of the Thyroid Hormones", 714, 714),
                    new SubtopicData("Physiological Functions of the Thyroid Hormones", 714, 717),
                    new SubtopicData("Regulation of Thyroid Hormone Secretion", 717, 719),
                    new SubtopicData("Diseases of Thyroid", 719, 722),
                    new SubtopicData("Endocrine Functions of the Thymus Gland", 722, 722),
                    new SubtopicData("Pineal Gland", 722, 724)
            )        
    ),
    
    CHAPTER_90(
            "Calcium Homeostasis",
            Arrays.asList(
                    
                    new SubtopicData("Physiological Effects of Calcium", 727, 728),
                    new SubtopicData("Outline of Calcium Metabolism", 728, 729),
                    new SubtopicData("Mechanism of Bone Calcification", 729, 731),
                    new SubtopicData("Parathyroid Hormone", 731, 733),
                    new SubtopicData("Vitamin D", 733, 735),
                    new SubtopicData("Calcitonin", 735, 735),
                    new SubtopicData("Summary of Control of Calcium Concentration", 735, 737),
                    new SubtopicData("Pathophysiology of Parathyroid Hormone, Vitamin D, and Bone Disease", 737, 738)
            )  
    ),
    
    CHAPTER_91(
            "Adrenal Cortex",
            Arrays.asList(
                    
                    new SubtopicData("Anatomy of Adrenal Cortex", 741, 742),
                    new SubtopicData("Corticosteroids: Mineralocorticoids, Glucocorticoids, Androgens", 742, 742),
                    new SubtopicData("Synthesis and Secretion of Adrenocortical Hormones", 742, 744),
                    new SubtopicData("Functions of the Mineralocorticoids - Aldosterone", 744, 747),
                    new SubtopicData("Functions of Glucocorticoids", 747, 751),
                    new SubtopicData("Adrenal Androgens", 751, 752),
                    new SubtopicData("Abnormalities of Adrenocortical Secretion", 752, 756)
            ) 
    ),
    
    CHAPTER_92(
            "Adrenal Medulla",
            Arrays.asList(
                    
                    new SubtopicData("Special Nature of the Sympathetic Nerve Endings in the Adrenal Medulla", 759, 759),
                    new SubtopicData("Biosynthesis of Epinephrine and Norepinephrine, Its Removal, and Its Duration of Action", 759, 759),
                    new SubtopicData("Factors That Control the Secretion of Catecholamines from the Adrenal Medulla", 759, 760),
                    new SubtopicData("Adrenergic Receptors - Alpha and Beta Receptors", 760, 761),
                    new SubtopicData("Physiological Effect of Catecholamines", 761, 764)
            )  
    ),
    
    CHAPTER_93(
            "Endocrine Pancreas and Glucose Homeostasis",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 767, 768),
                    new SubtopicData("Insulin and Its Metabolic Effects", 768, 773),
                    new SubtopicData("Glucagon and Its FUnctions", 773, 774),
                    new SubtopicData("Somatostatin Inhibits Glucagon adn Insulin Secretion", 775, 775),
                    new SubtopicData("Summary of Blood Glucose Regulation", 775, 775),
                    new SubtopicData("Diabetes Mellitus", 775, 780)
            )     
    ),
    
    CHAPTER_94(
            "Physiological Anatomy of the Male Sexual Organs and Spermatogenesis",
            Arrays.asList(
                    
                    new SubtopicData("Male Sexual Differentiation in Utero", 785, 786),
                    new SubtopicData("Spermatogenesis", 786, 789)
            )       
    ),
    
    CHAPTER_95(
            "Testosterone and Other Male Sex Hormones",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 791, 792),
                    new SubtopicData("Functions of Testosterone", 792, 794),
                    new SubtopicData("Basic Intracellular Mechanism of Action of Testosterone", 794, 794),
                    new SubtopicData("Control of Male Sexual Functions by Hormones from the Hypothalamus and Anterior Pituitary Gland", 794, 796)
            )        
    ),
    
    CHAPTER_96(
            "Female Physiology Before Pregnancy and Female Hormones",
            Arrays.asList(
                    
                    new SubtopicData("Physiological Anatomy of the Female Sexual Organs", 799, 799),
                    new SubtopicData("Oogenesis and Follicular Development in the Ovaries", 799, 801),
                    new SubtopicData("Female Hormonal System", 801, 801),
                    new SubtopicData("Female Monthly Sexual Cycle (Menstrual Cycle)", 801, 805),
                    new SubtopicData("Regulation of the Female Monthly Rhythm - Interplay Between the Ovarian and Hypothalamic - Pituitary Hormones", 805, 807),
                    new SubtopicData("Functions of the Ovarian Hormones - Estradiol and Progesterone", 807, 811),
                    new SubtopicData("Puberty and Menarche", 811, 811),
                    new SubtopicData("Menopause", 811, 812),
                    new SubtopicData("Rhythm Method of Contraception", 812, 812),
                    new SubtopicData("Hormonal Suppression of Fertility - \"The Pill\"", 812, 813)
            )        
    ),
    
    CHAPTER_97(
            "Sexual Act and Fertilization",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 815, 815),
                    new SubtopicData("Psychic Element of Male Sexual Stimulation", 815, 815),
                    new SubtopicData("Integration of the Male Sexual Act in the Spinal Cord", 815, 815),
                    new SubtopicData("Stages of Male Sexual Act", 815, 816),
                    new SubtopicData("Female Sexual Act", 816, 817),
                    new SubtopicData("Fertilization", 817, 818),
                    new SubtopicData("What Determines the Sex of the Fetus That is Created", 818, 819),
                    new SubtopicData("Cryptorchidism", 819, 821)
            )     
    ),
    
    CHAPTER_98(
            "Physiology of Pregnancy",
            Arrays.asList(
                    
                    new SubtopicData("Entry of the Ovum into the Fallopian Tube (Uterine Tube)", 823, 823),
                    new SubtopicData("Fertilization of the Ovum", 823, 823),
                    new SubtopicData("Transport of the Fertilized Ovum in the Fallopian Tube", 823, 824),
                    new SubtopicData("Implantation of the Blastocyst in the Uterus", 824, 824),
                    new SubtopicData("Early Nutrition of the Embryo", 824, 825),
                    new SubtopicData("Anatomy and Function of the Placenta", 825, 826),
                    new SubtopicData("Hormonal Factors in Pregnancy", 826, 828),
                    new SubtopicData("Response of the Mother's Body to Pregnancy", 828, 831)
            )       
    ),
    
    CHAPTER_99(
            "Parturition and Lactation",
            Arrays.asList(
                    
                    new SubtopicData("Parturition", 833, 836),
                    new SubtopicData("Lactation", 836, 838)
            )       
    ),
    
    CHAPTER_100(
            "Organization of Central Nervous System",
            Arrays.asList(
                    
                    new SubtopicData("General Design of the Nervous System", 843, 845),
                    new SubtopicData("Major Levels of Central Nervous System Function", 845, 846)
            )       
    ),
    
    CHAPTER_101(
            "Synapses",
            Arrays.asList(

                    new SubtopicData("Types of Synapses - Chemical and Electrical", 847, 848),
                    new SubtopicData("Physiological Anatomy of the Synapse", 848, 851),
                    new SubtopicData("Chemical Substances That Functions as Synaptic Transmitters", 851, 853),
                    new SubtopicData("Electrical Events During Neuronal Inhibition", 853, 854),
                    new SubtopicData("Some Special Characteristics of Synaptic Transmission (Box 101.1)", 854, 855)
            ) 
    ),
    
    CHAPTER_102(
            "Sensory Receptors",
            Arrays.asList(

                    new SubtopicData("Types of Sensory Receptors and the Stimuli they Detect", 857, 858),
                    new SubtopicData("Transduction of Sensory Stimuli into Nerve Impulse", 858, 860),
                    new SubtopicData("Nerve Fibers That Transmit Different Types of Signals and Their Physiological Classification", 860, 861),
                    new SubtopicData("Transmission of Signals of Different Intensity in Nerve Tracts - Spatial and Temporal Summation", 861, 861)

            )      
    ),
    
    CHAPTER_103(
            "Somatic Sensory Pathways",
            Arrays.asList(
                    
                    new SubtopicData("Classification of Somatic Senses", 863, 863),
                    new SubtopicData("Detection and Transmission of Tactile Sensations", 863, 864),
                    new SubtopicData("Sensory Pathways for Transmitting Somatic Signals into the Central Nervous System", 864, 865),
                    new SubtopicData("Transmission in the Dorsal Column - Medial Lemniscal System", 865, 867),
                    new SubtopicData("Transmission of Less Critical Sensory Signals in the Anterolateral Pathway", 867, 868),
                    new SubtopicData("Some Special Aspects of Somatosensory Function", 868, 869)
            )    
    ),
    
    CHAPTER_104(
            "Pain and Temperature",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 871, 871),
                    new SubtopicData("Types of Pain and Their Qualities - Fast Pain and Slow Pain", 871, 871),
                    new SubtopicData("Pain Receptors and Their Stimulation", 871, 872),
                    new SubtopicData("Dual Pathways for Transmission of Pain Signals into the Central Nervous System", 872, 874),
                    new SubtopicData("Pain Suppression (\"Analgesia\") System in the Brain and Spinal Cord", 874, 875),
                    new SubtopicData("Referred Pain", 875, 876),
                    new SubtopicData("Visceral Pain", 876, 877),
                    new SubtopicData("Some Clinical Abnormalities of Pain and Other Somatic Sensations", 877, 878),
                    new SubtopicData("Thermal Sensations", 878, 879)
            )      
    ),
    
    CHAPTER_105(
            "Somatosensory Cortex",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 881, 881),
                    new SubtopicData("Somatosensory Areas I and II", 881, 882),
                    new SubtopicData("Layers of Somatosensory Cortex and Their Function", 882, 882),
                    new SubtopicData("Functions od Somatosensory Area I", 883, 883),
                    new SubtopicData("Somatosensory Association Areas", 883, 883),
                    new SubtopicData("Overall Characteristics of Signal Transmission and Analysis in the Dorsal Column - Medial Lemniscal - Cortical System", 883, 884)
            )     
    ),
    
    CHAPTER_106(
            "Special Senses",
            Arrays.asList(
                    
                    new SubtopicData("Sense of Taste", 887, 890),
                    new SubtopicData("Sense of Smell", 890, 893)
            )      
    ),
    
    CHAPTER_107(
            "Hearing",
            Arrays.asList(
                    
                    new SubtopicData("Middle Ear", 895, 896),
                    new SubtopicData("Cochlea", 896, 900),
                    new SubtopicData("Central Auditory System", 900, 902),
                    new SubtopicData("Hearing Abnormalities", 902, 903),
                    new SubtopicData("Tympanogram and Tympanometry", 903, 903),
                    new SubtopicData("Auditory Evoked Potentials", 903, 903)
            )      
    ),
    
    CHAPTER_108(
            "Optics of Vision",
            Arrays.asList(
                    
                    new SubtopicData("Physical Principles of Optics", 905, 908),
                    new SubtopicData("Optics of the Eye", 908, 913),
                    new SubtopicData("Fluid System of the Eye - Intraocular Fluid", 913, 915)
            )
    ),
    
    CHAPTER_109(
            "The Retina",
            Arrays.asList(
                    
                    new SubtopicData("Anatomy and Function of the Structural Elements of the Retina", 917, 919),
                    new SubtopicData("Photochemistry of Vision", 919, 923),
                    new SubtopicData("Color Vision", 923, 924),
                    new SubtopicData("Neural Functions of the Retina", 924, 926)
            )     
    ),
    
    CHAPTER_110(
            "Visual Pathways and Central Processing",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 929, 931),
                    new SubtopicData("Organization and Function of the Visual Cortex", 931, 932),
                    new SubtopicData("Neuronal Patterns of Stimulation During Analysis of the Visual Image", 932, 933),
                    new SubtopicData("Fields of Vision; Perimetry", 933, 933), // The semicolon used in the subtopic title here is a stylistic choice,
                                                                                                      // indicating that Perimetry is a distinct yet closely related topic to Fields of Vision.
                                                                                                      // The usage of "and": would not suffice because "and" separates two distinct and euqally important topics however, Perimetry is like a sub-subtopic for Fields of Vision.
                    new SubtopicData("Eye Movements and Their Control", 933, 935),
                    new SubtopicData("Autonomic Control of Accommodation and Pupillary Aperture", 935, 936),
                    new SubtopicData("Visual Evoked Potentials", 936, 936)
            )
    ),
    
    CHAPTER_111(
            "Introduction to the Motor System: Spinal Cord",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 939, 939),
                    new SubtopicData("Organization of the Spinal Cord for Motor Functions", 939, 941),
                    new SubtopicData("Autonomic Reflexes in the Spinal Cord", 941, 941),
                    new SubtopicData("Spinal Cord Transection and Spinal Shock", 941, 942),
                    new SubtopicData("Hemisection of the Cord", 942, 942),
                    new SubtopicData("Tabes Dorsalis", 942, 942),
                    new SubtopicData("Syringomyelia", 942, 942)
            )    
    ),
    
    CHAPTER_112(
            "Cortical and Brainstem Control of Motor Function: The Pyramidal Tract",
            Arrays.asList(
                    
                    new SubtopicData("Motor Cortex and Corticospinal (Pyramidal) Tract", 945, 946),
                    new SubtopicData("Transmission of Signals from the Motor Cortex to the Muscles", 947, 947),
                    new SubtopicData("\"Extrapyramidal\" System", 947, 948)
            )       
    ),
    
    CHAPTER_113(
            "Muscle Spindle",
            Arrays.asList(
                    
                    new SubtopicData("Muscle Sensory Receptors - Muscle Spindle and Golgi Tendon Organs - and Their Roles in Muscle Control", 951, 953),
                    new SubtopicData("Clinical Applications of the Stretch Reflex", 953, 954),
                    new SubtopicData("Golgi's Tendon Reflex", 954, 955)
            )   
    ),
    
    CHAPTER_114(
            "Motor Reflexes",
            Arrays.asList(
                    
                    new SubtopicData("Reflexes", 957, 957),
                    new SubtopicData("Flexor Reflexes and the Withdrawal Reflexes", 957, 958),
                    new SubtopicData("Crossed Extensor Reflex", 958, 959),
                    new SubtopicData("Reciprocal Inhibition and Reciprocal Innervation", 959, 959),
                    new SubtopicData("Spinal Cord Reflexes that Cause Muscle Spasm", 959, 959)
            )      
    ),
    
    CHAPTER_115(
            "Regulation of Tone and Posture",
            Arrays.asList(
                    
                    new SubtopicData("Support of the Body Against Gravity - Roles of the Reticular and Vestibular Nuclei", 961, 962),
                    new SubtopicData("Decorticate Rigidity", 962, 962),
                    new SubtopicData("Postural and Locomotive Reflexes of the Cord", 962, 962)
            )      
    ),
    
    CHAPTER_116(
            "Cerebellum",
            Arrays.asList(
                    
                    new SubtopicData("Cerebellum and Its Motor Functions", 965, 972)
            )     
    ),
    
    CHAPTER_117(
            "Vestibular Apparatus",
            Arrays.asList(
                    
                    new SubtopicData("Maintenance of Equilibrium", 973, 975),
                    new SubtopicData("Functions of the vestibular System", 975, 975),
                    new SubtopicData("Detection of Head Rotation by the Semicircular Ducts", 975, 976),
                    new SubtopicData("Vestibular Mechanism for Stabilizing the Eyes", 976, 976),
                    new SubtopicData("Tests for Vestibular Function", 976, 976)
            ) 
    ),
    
    CHAPTER_118(
            "Basal Ganglia",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 977, 977),
                    new SubtopicData("Neuronal Circuitry", 977, 977),
                    new SubtopicData("Function of the Basal Ganglia in Executing Patterns of Motor Activity - the Putamen Circuit", 977, 979),
                    new SubtopicData("Role of the Basal Ganglia for Cognitive Control of Sequences of Motor Patterns - the Caudate Circuit", 979, 979),
                    new SubtopicData("Function of the Basal Ganglia to Change the Timing and to Scale the Intensity of Movements", 979, 980),
                    new SubtopicData("Summary of the Integrated Functions of the Basal Ganglia", 980, 981),
                    new SubtopicData("Clinical Syndromes Resulting from Damage to the Basal Ganglia", 981, 981)
            )
    ),
    
    CHAPTER_119(
            "The Autonomic Nervous System",
            Arrays.asList(
                    
                    new SubtopicData("General organization of the Autonomic Nervous System", 983, 986),
                    new SubtopicData("Basic Characteristics of Sympathetic and Parasympathetic Function", 986, 990),
                    new SubtopicData("Stimulation of Discrete Organs in Some Instances and \"Mass Stimulation\" in Other Instances by the Sympathetic and Parasympathetic Systems", 990, 991),
                    new SubtopicData("Pharmacology of the Autonomic Nervous System", 991, 992)
            )
    ),
    
    CHAPTER_120(
            "Functions of the Hypothalamus",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 995, 995),
                    new SubtopicData("Vegetative and Endocrine Control Functions of the Hypothalamus", 995, 998)
            )  
    ),
    
    CHAPTER_121(
            "Cerebrospinal Fluid",
            Arrays.asList(
                    
                    new SubtopicData("Cushioning Function of the Cerebrospinal Fluid", 999, 1000),
                    new SubtopicData("Formation, Flow, and Absorption of Cerebrospinal Fluid", 1000, 1001),
                    new SubtopicData("Cerebrospinal Fluid Pressure", 1001, 1001),
                    new SubtopicData("Blood-Cerebrospinal Fluid and Blood-Brain Barriers", 1001, 1002),
                    new SubtopicData("Brain Edema", 1002, 1002)
            )
    ),
    
    CHAPTER_122(
            "Electroencephalography, Epilepsy, and Meditation",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 1005, 1005),
                    new SubtopicData("Electroencephalography", 1005, 1007),
                    new SubtopicData("Seizures and Epilepsy", 1007, 1008),
                    new SubtopicData("Meditation", 1008, 1009)
            )   
    ),
    
    CHAPTER_123(
            "Sleep, Coma, and Brain Death",
            Arrays.asList(
                    
                    new SubtopicData("Two Types of Sleep - Slow-Wave Sleep and Rapid Eye Movement Sleep", 1011, 1012),
                    new SubtopicData("Basic Theories of Sleep", 1013, 1014),
                    new SubtopicData("Sleep Has Important Physiological Functions", 1014, 1015),
                    new SubtopicData("Coma and Brain Death", 1015, 1016)
            )        
    ),
    
    CHAPTER_124(
            "The Limbic System and Behaviour",
            Arrays.asList(
                    
                    new SubtopicData("Activating - Driving Systems of the Brain", 1017, 1020),
                    new SubtopicData("Limbic System", 1020, 1020),
                    new SubtopicData("Functional Anatomy of the Limbic System", 1020, 1021),
                    new SubtopicData("Specific Functions of Other Parts of the Limbic System", 1021, 1024)
            )        
    ),
    
    CHAPTER_125(
            "Learning and Memory",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 1027, 1027),
                    new SubtopicData("Learning", 1027, 1028),
                    new SubtopicData("Thoughts, Consciousness, and Memory", 1028, 1031),
                    new SubtopicData("Alzheimer's Disease - Amyloid Plaques amd Depressed Memory", 1031, 1032)
            )        
    ),
    
    CHAPTER_126(
            "Cortical Function, Cerebral Lateralization, and Speech",
            Arrays.asList(
                    
                    new SubtopicData("Physiological Anatomy of the Cerebral Cortex", 1035, 1036),
                    new SubtopicData("Functions of Specific Cortical Areas", 1036, 1040),
                    new SubtopicData("Function of the Brain in Communication - Language Input and Language Output", 1040, 1041),
                    new SubtopicData("Function of the Corpus Callosum and Anterior Commissure to Transfer Thoughts, Memories, Training, and Other Information Between the Two Cerebral Hemispheres", 1042, 1042)
            )        
    ),
    
    CHAPTER_127(
            "Components of Energy Expenditure",
            Arrays.asList(
                    
                    new SubtopicData("Components of 24-Hour Energy Expenditure", 1047, 1049),
                    new SubtopicData("Metabolic Rate and Its Measurement", 1049, 1050)
            )        
    ),
    
    CHAPTER_128(
            "Body Temperature Regulation and Cutaneous Circulation",
            Arrays.asList(
                    
                    new SubtopicData("Normal Body Temperature", 1053, 1053),
                    new SubtopicData("Body Temperature is Controlled by Balancing Heat Production and Heat Loss", 1054, 1054),
                    new SubtopicData("Cutaneous Circulation", 1054, 1056),
                    new SubtopicData("Sweating and Its Regulation by the Autonomic Nervous System", 1056, 1057),
                    new SubtopicData("Other Mechanisms of Heat Loss in Lower Animals - Panting", 1057, 1057),
                    new SubtopicData("Regulation of Body Temperature - Role of the Hypothalamus", 1057, 1060),
                    new SubtopicData("Behaviour Control of Body Temperature", 1060, 1060),
                    new SubtopicData("Abnormalities of Body Temperature Regulation", 1060, 1063)
            )        
    ),
    
    CHAPTER_129(
            "Physiology of Starvation and Obesity",
            Arrays.asList(
                    
                    new SubtopicData("Starvation", 1065, 1068),
                    new SubtopicData("Obesity", 1068, 1070)
            )        
    ),
    
    CHAPTER_130(
            "Physiology of Physical Activity and Inactivity",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 1071, 1071),
                    new SubtopicData("Physical Activity During the Day Occurs Across Multiple Domains", 1071, 1072),
                    new SubtopicData("Physical Activity Increases Energy Expenditure", 1072, 1073),
                    new SubtopicData("Physical Activity Has Health Benefits", 1073, 1075),
                    new SubtopicData("Models of Physical Inactivity in Humans", 1075, 1076),
                    new SubtopicData("Consequences of Physical Inactivity", 1076, 1078),
                    new SubtopicData("Sedentary Behaviour Such as Prolonged Sitting Can Offset Some of the Gains of Physical Activity and Exercise with Regard to Health", 1079, 1080)
            )        
    ),
    
    CHAPTER_131(
            "Physiological Response to Exercise in Extreme Heat and Cold",
            Arrays.asList(
                    
                    new SubtopicData("Cardiovascular Changes During Acute Exercise", 1081, 1083),
                    new SubtopicData("Respiratory Changes During Acute Exercise", 1083, 1083),
                    new SubtopicData("Increase in Muscle Temperature Within Physiological Limits Increases Muscle Performance", 1083, 1083),
                    new SubtopicData("Regulation of Cardiovascular and Respiratory Function During Acute Exercise", 1084, 1084),
                    new SubtopicData("Exercise Under Conditions of Heat Stress", 1084, 1085),
                    new SubtopicData("Exercise Under Conditions of Extreme Cold", 1086, 1086)
            )        
    ),
    
    CHAPTER_132(
            "Physiology of Stress",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 1089, 1090),
                    new SubtopicData("A Current Understanding of the Stress System", 1090, 1092)
            )        
    ),
    
    CHAPTER_133(
            "Transition from Fetus to Newborn, and Infancy",
            Arrays.asList(
                    
                    new SubtopicData("Cardiovascular and Circulatory Changes", 1095, 1096),
                    new SubtopicData("The Body Fluid and the Renal System", 1096, 1097),
                    new SubtopicData("Blood Cells and Immunity", 1097, 1097),
                    new SubtopicData("Respiratory System", 1097, 1098),
                    new SubtopicData("Central Nervous System", 1098, 1099),
                    new SubtopicData("Gastrointestinal Tract", 1099, 110),
                    new SubtopicData("Thermal Adaptation", 1100, 1101),
                    new SubtopicData("Physiology of Growth: Hormones and Related Factors", 1101, 1101)
            )        
    ),
    
    CHAPTER_134(
            "Physiological Changes With Aging",
            Arrays.asList(
                    
                    new SubtopicData("Introduction", 1103, 1103),
                    new SubtopicData("Definition of Aging", 1103, 1103),
                    new SubtopicData("Chronological and Biological Aging", 1103, 1103),
                    new SubtopicData("Theories of Aging", 1103, 1106),
                    new SubtopicData("System-Wise Changes that Occur with Aging", 1106, 1109)
            )        
    );
    
    private final String title;
    private final List<SubtopicData> subtopics;

    private Content(String title, List<SubtopicData> subtopics) {
        this.title = title;
        this.subtopics = subtopics;
    }

    public String getTitle() {
        return title;
    }

    public List<SubtopicData> getSubtopics() {
        return subtopics;
    }
    
    public static List<Content> getContentBySection(int section){
        
        List<Content> contentList = new ArrayList<>();
        
        switch (section) {
            case 1 -> {
                contentList = addContent(1, 7, contentList);
            }
            
            case 2 -> {
                contentList = addContent(8, 17, contentList);
            }
            
            case 3 -> {
                contentList = addContent(18, 28, contentList);
            }
            
            case 4 -> {
                contentList = addContent(29, 52, contentList);
            }
            
            case 5 -> {
                contentList = addContent(53, 64, contentList);
            }
            
            case 6 -> {
                contentList = addContent(65, 75, contentList);
            }
            
            case 7 -> {
                contentList = addContent(76, 84, contentList);
            }
            
            case 8 -> {
                contentList = addContent(85, 93, contentList);
            }
            
            case 9 -> {
                contentList = addContent(94, 99, contentList);
            }
            
            case 10 -> {
                contentList = addContent(100, 126, contentList);
            }
            
            case 11 -> {
                contentList = addContent(127, 134, contentList);
            }
            
        }
        
        return contentList;
    }
    
    private static List<Content> addContent (int from, int to, List<Content> intoList){
        
        for (int i = from; i <= to; i++){
            
            String enumName = "CHAPTER_" +Integer.toString(i);
            try{
                intoList.add(valueOf(enumName));
            }
            catch(IllegalArgumentException e){
                System.out.println("Invalid Enum name : " + enumName);
            }
        }
        
        return intoList;
    }
}
