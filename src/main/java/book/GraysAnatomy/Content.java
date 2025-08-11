/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.GraysAnatomy;

import backend.MajorTopicType;
import backend.SubtopicData;
import java.time.chrono.ThaiBuddhistEra;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author BRIN
 */
public enum Content {
    
    
    THE_BODY(
            "The body",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("What is anatomy?", 2, 4),
                        new SubtopicData("Imaging", 5, 10),
                        new SubtopicData("Image interpretation", 10,11),
                        new SubtopicData("Safety in imaging", 11, 11),
                        new SubtopicData("Skeletal system", 12, 24),
                        new SubtopicData("Skin and fascias", 24, 25),
                        new SubtopicData("Muscualar system", 25, 26),
                        new SubtopicData("Cardiovascular system", 27, 28),
                        new SubtopicData("Lymphatic system", 29, 31),
                        new SubtopicData("Nervous system", 31, 48),
                        new SubtopicData("Other system", 48, 48)
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        new SubtopicData("SkeletonData", 0, 0)
                        // This is only as a non functional entry, preventing any scope of dreaded NullPointerException
                        
                )); // The body has a very exceptioonal chapter organisation
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("SkeletonData", 0, 0)
                        // This is only as a non functional entry, preventing any scope of dreaded NullPointerException
                ));
                
            }}
    ),
    

    BACK(
            "Back",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        new SubtopicData("General Description", 53, 53),
                        new SubtopicData("Functions", 54, 55),
                        new SubtopicData("Component parts", 56, 60),
                        new SubtopicData("Relationship to other regions", 61, 62),
                        new SubtopicData("Key features", 62, 63)
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        new SubtopicData("Skeletal framework", 64, 77),
                        new SubtopicData("Joints", 77, 80),
                        new SubtopicData("Ligament", 80, 84),
                        new SubtopicData("Back musculature", 84, 99),
                        new SubtopicData("Spinal Cord", 99, 110)
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        new SubtopicData("Back surface Anatomy", 111, 111),
                        new SubtopicData("Absence of lateral curvatures", 111, 111),
                        new SubtopicData("Primary and secondary curvatures in sagittal plane", 112, 112),
                        new SubtopicData("Useful nonvertebral skeletal landmark", 112, 113),
                        new SubtopicData("How to identify specific vertebral spinous process", 114, 115),
                        new SubtopicData("Visualising the inferior ends of the spinal cord and subarachnoid space", 115, 116),
                        new SubtopicData("Identifying major muscles", 116, 117)
                ));
                
                
            }}
    ),
    
    THORAX(
            "Thorax",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("General Description", 123, 124),
                        new SubtopicData("Functions", 124, 124),
                        new SubtopicData("Component Parts", 124, 129),
                        new SubtopicData("Relationship to other regions", 130, 131),
                        new SubtopicData("Key features", 132, 138)
                        
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Pectoral region", 139, 143),
                        new SubtopicData("Thoracic region", 143, 160),
                        new SubtopicData("Diaphragm", 161,162),
                        new SubtopicData("Movements of the thoracic wall and diaphragm during breathing", 162, 162),
                        new SubtopicData("Pleural Cavities", 162, 179),
                        new SubtopicData("Mediastinum", 180, 230)
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Thorax surface anatomy", 231, 231),
                        new SubtopicData("How to count ribs", 231, 231),
                        new SubtopicData("Surface anatomy of breast in women", 232, 232),
                        new SubtopicData("Visualizing structures at the TIV/V vertebral level", 232, 233),
                        new SubtopicData("Visualizing structures in superior mediastinum", 234, 234),
                        new SubtopicData("Visualisong the margins of the heart", 235, 235),
                        new SubtopicData("Where to listen for heart sounds",236, 236),
                        new SubtopicData("Visualizing the pleural cavities and lungs, pleural resesses, and lung lobes and fissures", 235, 238),
                        new SubtopicData("Where to listen for lung sounds", 238, 240)
                ));
                
            }}
    ),
    
    ABDOMEN(
            "Abdomen",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("General description", 255, 256),
                        new SubtopicData("Functions", 256, 258),
                        new SubtopicData("Component Parts", 259, 263),
                        new SubtopicData("Relationship to other regions", 263, 265),
                        new SubtopicData("Key features", 265, 276)
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Surface Topography", 277, 279),
                        new SubtopicData("Abdominal wall", 280, 292),
                        new SubtopicData("Groin", 292, 302),
                        new SubtopicData("Abdominal viscera", 303, 365),
                        new SubtopicData("Posterior abdominal region", 366, 401)
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Abdomen surface anatomy", 402, 402),
                        new SubtopicData("Defining the surface projection of the abdomen", 402, 402),
                        new SubtopicData("How to find the superficial inguinal ring", 403, 404),
                        new SubtopicData("How to determing lumbar vertebral levels", 404, 405),
                        new SubtopicData("Visualizing structures at LI vertebral level", 405, 405),
                        new SubtopicData("Visualising the position of major blood vessels", 406, 406),
                        new SubtopicData("Using abdominal quadrants to locate major viscera", 407, 407),
                        new SubtopicData("Defining surface regions to which pain from gut is referred", 408, 408),
                        new SubtopicData("Where to find the kidneys", 409, 409),
                        new SubtopicData("Where to find the spleen", 409, 409)
                ));
                
            }}
    ),
    PELVIS_AND_PERINEUM(
            "Pelvis and perineum",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("General description", 423, 423),
                        new SubtopicData("Funtions", 423, 425),
                        new SubtopicData("Component parts", 426, 431),
                        new SubtopicData("Relationship to other regions", 432, 433),
                        new SubtopicData("Key features", 434, 440)
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Pelvis", 441, 502),
                        new SubtopicData("Perineum", 502, 519)
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Surface anatomy of pelvis and perineum", 520, 520),
                        new SubtopicData("Orientation of pelvis and perineum in  the anatomical position", 520, 520),
                        new SubtopicData("How to define the margins of the perineum", 520, 522),
                        new SubtopicData("Identification of structures in the anal triangle", 522, 522),
                        new SubtopicData("Identification of structures in the urogenital triangle of women", 523, 524),
                        new SubtopicData("Identification of structures in the urogenital triangle of men", 524, 526)
                ));
                
            }}
    ),
    LOWER_LIMB(
            "Lower limb",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("General introduction", 535, 537),
                        new SubtopicData("Function", 537, 539),
                        new SubtopicData("Component parts", 539, 544),
                        new SubtopicData("Relationship to other regions", 545, 545),
                        new SubtopicData("Key points", 545, 550)
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("<Bony Pelvis> (No exact subtopic title available)", 551, 573),
                        new SubtopicData("Gluteal region", 574, 583),
                        new SubtopicData("Thigh", 583, 617),
                        new SubtopicData("Leg", 618, 633),
                        new SubtopicData("Foot", 633, 662)
                        
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Lower limb surface anatomy", 663, 663),
                        new SubtopicData("Avoiding the sciatic nerve", 663, 663),
                        new SubtopicData("Finding the femoral artery in the femoral triangle", 664, 664),
                        new SubtopicData("Identifying structures around the knee", 664, 665),
                        new SubtopicData("Visualising the contents of the popliteal fossa", 666, 666),
                        new SubtopicData("finding the tarsal tunnel-the gateway to the foot", 667, 667),
                        new SubtopicData("Identifying tendons around the ankle and in the foot", 668, 669),
                        new SubtopicData("Finding the dorsalis pedis artery", 669, 669),
                        new SubtopicData("Approximating the position of the plantar arterial arch", 669, 669),
                        new SubtopicData("Major superficial veins", 670, 670),
                        new SubtopicData("Pulse points", 671, 671)
                ));
                
            }}
    ),
    
    UPPER_LIMB(
            "Upper limb",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("General description", 685, 686),
                        new SubtopicData("Functions", 686, 690),
                        new SubtopicData("Component parts", 690, 693),
                        new SubtopicData("Relationship to other regions", 693, 695),
                        new SubtopicData("Key points", 695, 701)
                        
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Shoulder", 702, 716), // Just one line in page 716 lmao
                        new SubtopicData("Posterior scapular region", 716, 721),
                        new SubtopicData("Axilla", 721, 749),
                        new SubtopicData("Arm", 750, 763),
                        new SubtopicData("Elbow joint", 764, 768),
                        new SubtopicData("Cubital fossa", 768, 770),
                        new SubtopicData("Forearm", 771, 776),
                        new SubtopicData("Anterior component of the forearm", 776, 785),
                        new SubtopicData("Posterior component of the forearm", 785, 792),
                        new SubtopicData("Hand", 792, 818)
                        
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Upper limb surface anatomy", 819, 819),
                        new SubtopicData("Bony landmarks and muscles of the posterior scapular region", 819, 819),
                        new SubtopicData("Visualizing the axilla and locating contents and related structures", 820, 821),
                        new SubtopicData("Locating brachial arterty in the arm", 821, 821),
                        new SubtopicData("The triceps brachii tendon and position of radial nerve", 822, 822),
                        new SubtopicData("Cubital fossa (anterior view)", 822, 823),
                        new SubtopicData("Identifying tendons and locating major vessels and nerves in the distal forearm", 824, 825),
                        new SubtopicData("Normal appearance of the hand", 825, 825),
                        new SubtopicData("Postion of the flexor retinaculum and the recurrent branch of the median nerve", 826, 826),
                        new SubtopicData("Motor functions od the median and ulnar nerves in the hand", 826, 827),
                        new SubtopicData("Visualizing the position of the superficial and deep palmar arches", 827, 827),
                        new SubtopicData("Pulse points", 827, 827)
                ));
                
            }}
    ),
    HEAD_AND_NECK(
            "Head and neck",
            new LinkedHashMap<MajorTopicType, List<SubtopicData>>() {{ // Initialize with LinkedHashMap
                put(MajorTopicType.CONCEPTUAL_OVERVIEW, Arrays.asList(
                        
                        new SubtopicData("General description", 837, 841),
                        new SubtopicData("Functions", 841, 841),
                        new SubtopicData("Component parts", 842, 846),
                        new SubtopicData("Relationship to other regions", 847, 848),
                        new SubtopicData("Key features", 848, 854)
                ));
                put(MajorTopicType.REGIONAL_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Skull", 855, 864), // Single line in pg 864 lmao
                        new SubtopicData("Cranial cavity", 864, 872),
                        new SubtopicData("Meninges", 873, 878),
                        new SubtopicData("Brain and its blood supply", 879, 893),
                        new SubtopicData("Cranial nerves", 894, 903),
                        new SubtopicData("Face", 904, 921),
                        new SubtopicData("Scalp", 922, 927),
                        new SubtopicData("Orbit", 927, 952),
                        new SubtopicData("Ear", 953, 971),
                        new SubtopicData("Temporal and infratemporal fossae", 972, 992),
                        new SubtopicData("Pterygopalatine fossa", 992, 999),
                        new SubtopicData("Neck", 1000, 1040),
                        new SubtopicData("Pharynx", 1040, 1052),
                        new SubtopicData("Larynx", 1052, 1069),
                        new SubtopicData("Nasal cavities", 1069, 1087),
                        new SubtopicData("Oral cavities", 1087, 1119)
                ));
                put(MajorTopicType.SURFACE_ANATOMY, Arrays.asList(
                        
                        new SubtopicData("Head and neck surface anatomy", 1120, 1120),
                        new SubtopicData("Anatomical position of the head amd major landmarks", 1120, 1121),
                        new SubtopicData("Visualizing structures at the CIII/CIV and CVI vertebral levels", 1121, 1121),
                        new SubtopicData("How to outline the anterior and posterior triangle sof the neck", 1122, 1122),
                        new SubtopicData("How to locate cricothyroid ligament", 1123, 1124),
                        new SubtopicData("How to find thyroid gland", 1124, 1124),
                        new SubtopicData("Estimating the postion of the middle meningeal artery", 1124, 1125),
                        new SubtopicData("Major features of the face", 1125, 1126),
                        new SubtopicData("The eye and lacrimal apparatus", 1126, 1127),
                        new SubtopicData("External ear", 1127, 1127),
                        new SubtopicData("Pulse points", 1128, 1128)
                ));
                
            }}
    );
    
    
    private final String TITLE;
    private final Map<MajorTopicType, List<SubtopicData>> MAP;
    
    private Content(String title, Map<MajorTopicType, List<SubtopicData>> majorTopicSubtopicsMap) {
        this.TITLE = title;
        this.MAP = majorTopicSubtopicsMap;
    }
    
    public String getTitle(){
     return TITLE;   
    }
    
    public Map<MajorTopicType, List<SubtopicData>> getMajorTOpicMAp(){
    
    return Collections.unmodifiableMap(MAP); //returns an unmodifiable map
    }
    
    public List<SubtopicData> getCONCEPTUAL_OVERVIEW(){
        
        return MAP.get(MajorTopicType.CONCEPTUAL_OVERVIEW);
    }
    
    public List<SubtopicData> getREGIONAL_ANATOMY(){
        
        return MAP.get(MajorTopicType.REGIONAL_ANATOMY);
    }
    
    public  List<SubtopicData> getSURFACE_ANATOMY(){
        
        return MAP.get(MajorTopicType.SURFACE_ANATOMY);
    }
    
    private  List<SubtopicData> getContentType(Content content, int turn){
        
        if(turn == 0){
            return content.getCONCEPTUAL_OVERVIEW();
        }
        else if (turn == 1){
            return content.getREGIONAL_ANATOMY();
        }
        else{
            return content.getSURFACE_ANATOMY();
        }
    }
    
    private static String getMajorTopicTypeForLOOPER(int turn){
        
        if(turn == 0){
            
            return MajorTopicType.getCONCEPTUAL_OVERVIEW();
        }
        else if(turn == 1){
            
            return MajorTopicType.getREGIONAL_ANATOMY();
        }
        else{
            return MajorTopicType.getSURFACE_ANATOMY();
        }
    }
    
    
    
    private  Map<Integer, String[]> getEverythingByPageLOOPER(Map<Integer, String[]> returnMap, Content content, int page){
        
        int count = returnMap.size();
        
        
        for(int turn = 0; turn < 3; turn++){
            
        List<SubtopicData> backList = getContentType(content, turn);
        
        
        for(int i = 0; i < backList.size(); i++){
            
            SubtopicData sub = backList.get(i);
            int startPage = sub.getStartPage();
            int endPage  = sub.getEndPage();
            
            if(page >= startPage && page <= endPage){
        
                
                returnMap.put(count, new String[]{
                    content.getTitle(), //index = 0
                    getMajorTopicTypeForLOOPER(turn), // index = 1
                    sub.getTitle(), // Index = 2
                    Integer.toString(sub.getStartPage()), // index = 3
                    Integer.toString(sub.getEndPage()) // index = 4
                
                });
                count++; // Map starts at index 0;
            }
        }
        
        //TODO MORE
        }
        
        return returnMap;
    }
    
    public Map<Integer, String[]> getEverythingByPage(int page){ 
        
        Map<Integer, String[]> returnMap = new HashMap<>();
        
        returnMap = getEverythingByPageLOOPER(returnMap, Content.THE_BODY, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.BACK, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.THORAX, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.ABDOMEN, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.PELVIS_AND_PERINEUM, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.LOWER_LIMB, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.UPPER_LIMB, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Content.HEAD_AND_NECK, page);
        
        return returnMap;
    }
    
}
