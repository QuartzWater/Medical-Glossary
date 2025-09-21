/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package frontend.v2.window;

import backend.eventadapter.GranularMouseAdapter;
import frontend.RoundedPanel;
import frontend.SVGIconPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author BRIN
 */
public class ColorPicker extends javax.swing.JDialog {    
    
    private final int MAXIMUM_RECENTS;
    
    private static final Color SAVE_CANCEL_DEFAULT_BG = new Color(15,20,25);
    private static final Color SAVE_HOVER_BG = new Color(43,181,114);
    private static final Color SAVE_PRESSED_BG = new Color(37,160,100);
    
    private static final Color CANCEL_HOVER_BG = new Color(255,51,51);
    private static final Color CANCEL_PRESSED_BG = new Color(224,51,51);
    
    
    private final RoundedPanel[] foregroundColors;
    private final RoundedPanel[] backgroundColors;
    
    private final RoundedPanel[] recentForegroundColors;
    private final RoundedPanel[] recentBackgroundColors;
    
    private Color currentFG;
    private Color currentBG;
    
    private boolean approved = false;
    
    private final List<Color> recentFGList = new ArrayList<>();
    private final List<Color> recentBGList = new ArrayList<>();

    /**
     * Creates new form ColorPicker
     */
    private ColorPicker(Color currentFG, Color currentBG) {
        super((JFrame) null, "Pick a color", true); // casting it to JFrame makes it clear that null value is for JFrame specifically.
                                    // this is beacuse simply passing null won't tell compiler which constructor to use.
                                    // since super(null, true) can refer to either super(JFrame, true) or super(JDialog, true).
        initComponents();
        
        
        this.currentFG = currentFG;
        this.currentBG = currentBG;
        
        Color[] colors = {
                    new Color(148, 0, 211), // Violet
                    new Color(75, 0, 130),  // Indigo
                    new Color(0, 0, 255),   // Blue
                    new Color(0, 128, 0),   // Green
                    new Color(255, 255, 0), // Yellow
                    new Color(255, 165, 0), // Orange
                    new Color(255, 0, 0)    // Red
                };

        // Define the fractions (where each color appears)
        float[] fractions = {0.0f, 0.166f, 0.333f, 0.5f, 0.666f, 0.833f, 1.0f};
        
        float prefHeight = (float) otherfg.getPreferredSize().getHeight();
        float prefWidth = (float) otherfg.getPreferredSize().getWidth();
        
        otherfg.definePositionsForLGP(0.0f, prefHeight, prefWidth, 0.0f);
        otherbg.definePositionsForLGP(0.0f, prefHeight, prefWidth, 0.0f);
        
        otherfg.enableLinearGradientPaint(fractions, colors);
        otherbg.enableLinearGradientPaint(fractions, colors);
        
        foregroundColors = new RoundedPanel[]{
            
            f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13
        };
        
        backgroundColors = new RoundedPanel[]{
            
            b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13
        };
        
        recentForegroundColors = new RoundedPanel[]{
          
            rf1,rf2,rf3,rf4,rf5,rf6,rf7,rf8
        };
        
        recentBackgroundColors = new RoundedPanel[]{
            
            rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8
        };
        
        // ---- IMPORTANT -----
        MAXIMUM_RECENTS = recentBackgroundColors.length;
        // --------------------
        
        for(RoundedPanel rdb : foregroundColors){
            rdb.addMouseListener(fgGran);
            rdb.addMouseListener(effectsGran);
            rdb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        for(RoundedPanel rdb : backgroundColors){
            rdb.addMouseListener(bgGran);
            rdb.addMouseListener(effectsGran);
            rdb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        for(RoundedPanel rdb : recentForegroundColors){
            rdb.addMouseListener(recentFGGran);
            rdb.addMouseListener(effectsGran);
            rdb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        for(RoundedPanel rdb : recentBackgroundColors){
            rdb.addMouseListener(recentBGGran);
            rdb.addMouseListener(effectsGran);
            rdb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        noBG.addMouseListener(noBGGran);
        noBG.addMouseListener(noBGEffectsGran);
        noBG.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        otherfg.addMouseListener(otherFGGran);
        otherfg.addMouseListener(effectsGran);
        otherfg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        otherbg.addMouseListener(otherBGGran);
        otherbg.addMouseListener(effectsGran);
        otherbg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        saveButton.addActionListener(saveActionListen);
        saveButton.addMouseListener(saveEffectsGran);
        
        cancelButton.addActionListener(cancelActionListen);
        cancelButton.addMouseListener(cancelEffectsGran);
        
        
        StyledDocument doc = previewTextPane.getStyledDocument();
        Element element = doc.getCharacterElement(0);
        
        if(element == null){
            return;
        }
        MutableAttributeSet newAttr = new SimpleAttributeSet(element.getAttributes());
            
        StyleConstants.setForeground(newAttr, currentFG);
        if(currentBG != null){
            System.out.println("NOT NULL IN COLOR PICKER");
            StyleConstants.setBackground(newAttr, currentBG);
        }
        doc.setCharacterAttributes(0, doc.getLength(), newAttr, true);
    }
    
    public static Color[] showDialog(Color initialFG, Color initialBG){
        
        ColorPicker colorPick = new ColorPicker(initialFG, initialBG);
        colorPick.setVisible(true);
        
        if(colorPick.approved){
            return new Color[]{colorPick.currentFG, colorPick.currentBG};
        }else {
            return null;
        }
    }
    
    private GranularMouseAdapter fgGran = new GranularMouseAdapter(){
            
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            recentFGGran.actOnMouseRelease(e);
            recentFGList.addFirst(currentFG);
            if(recentFGList.size() > MAXIMUM_RECENTS){
                recentFGList.removeLast();
            }
            updateRecents(recentForegroundColors, recentFGList);
        }
    };
    
    private GranularMouseAdapter bgGran = new GranularMouseAdapter(){
            
        @Override
        public void actOnMouseRelease(MouseEvent e){
            recentBGGran.actOnMouseRelease(e);
            recentBGList.addFirst(currentBG);
            if(recentBGList.size() > MAXIMUM_RECENTS){
                recentBGList.removeLast();
            }
            updateRecents(recentBackgroundColors, recentBGList);
        }
    };
    
    private GranularMouseAdapter recentFGGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            RoundedPanel source = (RoundedPanel) e.getSource();
            StyledDocument doc = previewTextPane.getStyledDocument();
            
            Element characterElement = doc.getCharacterElement(0);
            if(characterElement == null){
                return;
            }
            
            MutableAttributeSet newAttr = new SimpleAttributeSet(characterElement.getAttributes());
            currentFG = source.getCurrentColor();
            
            StyleConstants.setForeground(newAttr, currentFG);
            doc.setCharacterAttributes(0, doc.getLength(), newAttr, true);
        }
    };
    
    private GranularMouseAdapter recentBGGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            RoundedPanel source = (RoundedPanel) e.getSource();
            currentBG = source.getCurrentColor();
            StyledDocument doc = previewTextPane.getStyledDocument();
            
            Element characterElement = doc.getCharacterElement(0);
            if(characterElement == null){
                return;
            }
            
            MutableAttributeSet newAttr = new SimpleAttributeSet(characterElement.getAttributes());
            
            StyleConstants.setBackground(newAttr, currentBG);
            doc.setCharacterAttributes(0, doc.getLength(), newAttr, true);
        }
    };
    
    private GranularMouseAdapter effectsGran = new GranularMouseAdapter(){
        
        double prevPadding = 1.0;
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            RoundedPanel element = (RoundedPanel) e.getSource();
            
            prevPadding = element.getSquarePadding();
            element.setSquarePadding(0.0);
            element.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            RoundedPanel element = (RoundedPanel) e.getSource();
            
            element.setSquarePadding(prevPadding);
            element.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            RoundedPanel element = (RoundedPanel) e.getSource();
            
            element.setSquarePadding(2.5);
            element.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            RoundedPanel element = (RoundedPanel) e.getSource();
            
            element.setSquarePadding(0.0);
            element.repaint();
        }
    };
    
    
    private GranularMouseAdapter otherFGGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            Color returnValue = JColorChooser.showDialog(getMyself(), "Choose from more colors!", currentFG);
            if(returnValue != null){
                currentFG = returnValue;
                StyledDocument doc = previewTextPane.getStyledDocument();
            
                Element characterElement = doc.getCharacterElement(0);
                if(characterElement == null){
                    return;
                }

                MutableAttributeSet newAttr = new SimpleAttributeSet(characterElement.getAttributes());

                StyleConstants.setForeground(newAttr, returnValue);
                doc.setCharacterAttributes(0, doc.getLength(), newAttr, true);
                recentFGList.addFirst(returnValue);
                if(recentFGList.size() > MAXIMUM_RECENTS){
                    recentFGList.removeLast();
                }
                updateRecents(recentForegroundColors, recentFGList);
            }
        }
    };
    
    private GranularMouseAdapter otherBGGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            Color returnValue;
            if(currentBG != null){
                returnValue = JColorChooser.showDialog(getMyself(), "Choose from more colors!", currentBG);
            }else{
                System.out.println("HERRRRRREEEEE");
                returnValue = JColorChooser.showDialog(getMyself(), "Choose from more colors!", Color.WHITE);
            }
            
            if(returnValue != null){
                currentBG = returnValue;
                StyledDocument doc = previewTextPane.getStyledDocument();
            
                Element characterElement = doc.getCharacterElement(0);
                if(characterElement == null){
                    return;
                }

                MutableAttributeSet newAttr = new SimpleAttributeSet(characterElement.getAttributes());

                StyleConstants.setBackground(newAttr, returnValue);
                doc.setCharacterAttributes(0, doc.getLength(), newAttr, true);
                
                recentBGList.addFirst(returnValue);
                if(recentBGList.size() > MAXIMUM_RECENTS){
                    recentBGList.removeLast();
                }
                updateRecents(recentBackgroundColors, recentBGList);
                
            }
        }
    };
    
    private GranularMouseAdapter noBGGran = new GranularMouseAdapter(){
            
        @Override
        public void actOnMouseRelease(MouseEvent e){

            StyledDocument doc = previewTextPane.getStyledDocument();

            Element characterElement = doc.getCharacterElement(0);
            if(characterElement == null){
                return;
            }

            MutableAttributeSet newAttr = new SimpleAttributeSet(characterElement.getAttributes());

            currentBG = null;
            newAttr.removeAttribute(StyleConstants.Background);
            doc.setCharacterAttributes(0, doc.getLength(), newAttr, true);
        }
            
    };
    
    private GranularMouseAdapter noBGEffectsGran = new GranularMouseAdapter(){
        
        double prevBGSqrPad = 0.0;
        double prevIconSqrPad = 3.0;
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            prevBGSqrPad = icon.getbgSquarePadding();
            prevIconSqrPad = icon.getIconSquarePadding();
            
            icon.setBackgroundPainted(true);
            icon.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            icon.setBackgroundPainted(false);
            icon.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            icon.setbgSquarePadding(2.5);
            icon.setIconSquarePadding(prevIconSqrPad + 2.0);
            icon.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            icon.setbgSquarePadding(prevBGSqrPad);
            icon.setIconSquarePadding(prevIconSqrPad);
            icon.repaint();
        }
    };
    
    private ActionListener saveActionListen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            approved = true;
            dispose();
        }
    };
    
    private GranularMouseAdapter saveEffectsGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            saveButton.setCurrentColor(SAVE_HOVER_BG);
            saveButton.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            
            saveButton.setCurrentColor(SAVE_CANCEL_DEFAULT_BG);
            saveButton.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            
            saveButton.setCurrentColor(SAVE_PRESSED_BG);
            saveButton.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            saveButton.setCurrentColor(SAVE_HOVER_BG);
            saveButton.repaint();
        }
        
    };
    
    private ActionListener cancelActionListen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            approved = false;
            dispose();
        }
    };
    
    private GranularMouseAdapter cancelEffectsGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            cancelButton.setCurrentColor(CANCEL_HOVER_BG);
            cancelButton.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            cancelButton.setCurrentColor(SAVE_CANCEL_DEFAULT_BG);
            cancelButton.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            cancelButton.setCurrentColor(CANCEL_PRESSED_BG);
            cancelButton.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            cancelButton.setCurrentColor(CANCEL_HOVER_BG);
            cancelButton.repaint();
        }
        
    };
    
    
    private void updateRecents(RoundedPanel[] recents, List<Color> recentColorList){
        int index = 0;
        
        for(RoundedPanel rdp : recents){
            try{
                rdp.setCurrentColor(recentColorList.get(index));
            }catch(Exception e){
                break;
            }
            index++;
        }
        recentContainer.repaint();
    }
    
    private ColorPicker getMyself(){
        
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        foregroundHeader = new javax.swing.JLabel();
        f1 = new frontend.RoundedPanel();
        f2 = new frontend.RoundedPanel();
        f3 = new frontend.RoundedPanel();
        f4 = new frontend.RoundedPanel();
        f5 = new frontend.RoundedPanel();
        f6 = new frontend.RoundedPanel();
        f7 = new frontend.RoundedPanel();
        f8 = new frontend.RoundedPanel();
        f9 = new frontend.RoundedPanel();
        f10 = new frontend.RoundedPanel();
        f11 = new frontend.RoundedPanel();
        f12 = new frontend.RoundedPanel();
        f13 = new frontend.RoundedPanel();
        otherfg = new frontend.RoundedPanel();
        jLabel2 = new javax.swing.JLabel();
        backgroundHeader = new javax.swing.JLabel();
        b1 = new frontend.RoundedPanel();
        b2 = new frontend.RoundedPanel();
        b3 = new frontend.RoundedPanel();
        b4 = new frontend.RoundedPanel();
        b5 = new frontend.RoundedPanel();
        b10 = new frontend.RoundedPanel();
        b7 = new frontend.RoundedPanel();
        b8 = new frontend.RoundedPanel();
        b9 = new frontend.RoundedPanel();
        b6 = new frontend.RoundedPanel();
        b11 = new frontend.RoundedPanel();
        b12 = new frontend.RoundedPanel();
        otherbg = new frontend.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        recentContainer = new javax.swing.JPanel();
        recentHeader = new javax.swing.JLabel();
        rf1 = new frontend.RoundedPanel();
        rf2 = new frontend.RoundedPanel();
        rf3 = new frontend.RoundedPanel();
        rf4 = new frontend.RoundedPanel();
        rf5 = new frontend.RoundedPanel();
        rf6 = new frontend.RoundedPanel();
        rf7 = new frontend.RoundedPanel();
        rf8 = new frontend.RoundedPanel();
        rb1 = new frontend.RoundedPanel();
        rb2 = new frontend.RoundedPanel();
        rb3 = new frontend.RoundedPanel();
        rb4 = new frontend.RoundedPanel();
        rb5 = new frontend.RoundedPanel();
        rb6 = new frontend.RoundedPanel();
        rb7 = new frontend.RoundedPanel();
        rb8 = new frontend.RoundedPanel();
        previewHeader = new javax.swing.JLabel();
        sep1 = new frontend.RoundedPanel();
        previewScroll = new javax.swing.JScrollPane();
        previewTextPane = new javax.swing.JTextPane();
        sep2 = new frontend.RoundedPanel();
        cancelButton = new frontend.RoundedButton();
        saveButton = new frontend.RoundedButton();
        noBG = new frontend.SVGIconPanel();
        b13 = new frontend.RoundedPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 550));
        setSize(new java.awt.Dimension(900, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainContainer.setBackground(new java.awt.Color(39, 52, 65));
        mainContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        foregroundHeader.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        foregroundHeader.setForeground(new java.awt.Color(255, 255, 255));
        foregroundHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foregroundHeader.setText("Foreground");
        mainContainer.add(foregroundHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 140, 30));

        f1.setArcSize(15);
        f1.setCurrentColor(new java.awt.Color(153, 0, 255));
        f1.setSquarePadding(1.0);

        javax.swing.GroupLayout f1Layout = new javax.swing.GroupLayout(f1);
        f1.setLayout(f1Layout);
        f1Layout.setHorizontalGroup(
            f1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f1Layout.setVerticalGroup(
            f1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 40, 40));

        f2.setArcSize(15);
        f2.setCurrentColor(new java.awt.Color(255, 0, 204));
        f2.setSquarePadding(1.0);

        javax.swing.GroupLayout f2Layout = new javax.swing.GroupLayout(f2);
        f2.setLayout(f2Layout);
        f2Layout.setHorizontalGroup(
            f2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f2Layout.setVerticalGroup(
            f2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 40, 40));

        f3.setArcSize(15);
        f3.setCurrentColor(new java.awt.Color(255, 204, 204));
        f3.setSquarePadding(1.0);

        javax.swing.GroupLayout f3Layout = new javax.swing.GroupLayout(f3);
        f3.setLayout(f3Layout);
        f3Layout.setHorizontalGroup(
            f3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f3Layout.setVerticalGroup(
            f3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 40, 40));

        f4.setArcSize(15);
        f4.setCurrentColor(new java.awt.Color(153, 153, 255));
        f4.setSquarePadding(1.0);

        javax.swing.GroupLayout f4Layout = new javax.swing.GroupLayout(f4);
        f4.setLayout(f4Layout);
        f4Layout.setHorizontalGroup(
            f4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f4Layout.setVerticalGroup(
            f4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 40, 40));

        f5.setArcSize(15);
        f5.setCurrentColor(new java.awt.Color(255, 51, 51));
        f5.setSquarePadding(1.0);

        javax.swing.GroupLayout f5Layout = new javax.swing.GroupLayout(f5);
        f5.setLayout(f5Layout);
        f5Layout.setHorizontalGroup(
            f5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f5Layout.setVerticalGroup(
            f5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 40, 40));

        f6.setArcSize(15);
        f6.setCurrentColor(new java.awt.Color(255, 175, 0));
        f6.setSquarePadding(1.0);

        javax.swing.GroupLayout f6Layout = new javax.swing.GroupLayout(f6);
        f6.setLayout(f6Layout);
        f6Layout.setHorizontalGroup(
            f6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f6Layout.setVerticalGroup(
            f6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 40, 40));

        f7.setArcSize(15);
        f7.setCurrentColor(new java.awt.Color(102, 255, 255));
        f7.setSquarePadding(1.0);

        javax.swing.GroupLayout f7Layout = new javax.swing.GroupLayout(f7);
        f7.setLayout(f7Layout);
        f7Layout.setHorizontalGroup(
            f7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f7Layout.setVerticalGroup(
            f7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 40, 40));

        f8.setArcSize(15);
        f8.setCurrentColor(new java.awt.Color(102, 255, 0));
        f8.setSquarePadding(1.0);

        javax.swing.GroupLayout f8Layout = new javax.swing.GroupLayout(f8);
        f8.setLayout(f8Layout);
        f8Layout.setHorizontalGroup(
            f8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f8Layout.setVerticalGroup(
            f8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 40, 40));

        f9.setArcSize(15);
        f9.setCurrentColor(new java.awt.Color(255, 255, 51));
        f9.setSquarePadding(1.0);

        javax.swing.GroupLayout f9Layout = new javax.swing.GroupLayout(f9);
        f9.setLayout(f9Layout);
        f9Layout.setHorizontalGroup(
            f9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f9Layout.setVerticalGroup(
            f9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 40, 40));

        f10.setArcSize(15);
        f10.setCurrentColor(new java.awt.Color(255, 153, 0));
        f10.setSquarePadding(1.0);

        javax.swing.GroupLayout f10Layout = new javax.swing.GroupLayout(f10);
        f10.setLayout(f10Layout);
        f10Layout.setHorizontalGroup(
            f10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f10Layout.setVerticalGroup(
            f10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 40, 40));

        f11.setArcSize(15);
        f11.setCurrentColor(new java.awt.Color(51, 153, 255));
        f11.setSquarePadding(1.0);

        javax.swing.GroupLayout f11Layout = new javax.swing.GroupLayout(f11);
        f11.setLayout(f11Layout);
        f11Layout.setHorizontalGroup(
            f11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f11Layout.setVerticalGroup(
            f11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 40, 40));

        f12.setArcSize(15);
        f12.setCurrentColor(new java.awt.Color(172, 255, 172));
        f12.setSquarePadding(1.0);

        javax.swing.GroupLayout f12Layout = new javax.swing.GroupLayout(f12);
        f12.setLayout(f12Layout);
        f12Layout.setHorizontalGroup(
            f12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f12Layout.setVerticalGroup(
            f12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 40, 40));

        f13.setArcSize(15);
        f13.setCurrentColor(new java.awt.Color(255, 255, 255));
        f13.setSquarePadding(1.0);

        javax.swing.GroupLayout f13Layout = new javax.swing.GroupLayout(f13);
        f13.setLayout(f13Layout);
        f13Layout.setHorizontalGroup(
            f13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        f13Layout.setVerticalGroup(
            f13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(f13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 40, 40));

        otherfg.setArcSize(15);
        otherfg.setSquarePadding(1.0);
        otherfg.setPreferredSize(new java.awt.Dimension(90, 40));
        otherfg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("More");
        otherfg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        mainContainer.add(otherfg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 90, 40));

        backgroundHeader.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        backgroundHeader.setForeground(new java.awt.Color(255, 255, 255));
        backgroundHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundHeader.setText("Background");
        mainContainer.add(backgroundHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 140, 30));

        b1.setArcSize(15);
        b1.setCurrentColor(new java.awt.Color(153, 102, 255));
        b1.setSquarePadding(1.0);

        javax.swing.GroupLayout b1Layout = new javax.swing.GroupLayout(b1);
        b1.setLayout(b1Layout);
        b1Layout.setHorizontalGroup(
            b1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b1Layout.setVerticalGroup(
            b1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 40, 40));

        b2.setArcSize(15);
        b2.setCurrentColor(new java.awt.Color(255, 153, 255));
        b2.setSquarePadding(1.0);

        javax.swing.GroupLayout b2Layout = new javax.swing.GroupLayout(b2);
        b2.setLayout(b2Layout);
        b2Layout.setHorizontalGroup(
            b2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b2Layout.setVerticalGroup(
            b2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 40, 40));

        b3.setArcSize(15);
        b3.setCurrentColor(new java.awt.Color(255, 204, 204));
        b3.setSquarePadding(1.0);

        javax.swing.GroupLayout b3Layout = new javax.swing.GroupLayout(b3);
        b3.setLayout(b3Layout);
        b3Layout.setHorizontalGroup(
            b3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b3Layout.setVerticalGroup(
            b3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 40, 40));

        b4.setArcSize(15);
        b4.setCurrentColor(new java.awt.Color(176, 176, 255));
        b4.setSquarePadding(1.0);

        javax.swing.GroupLayout b4Layout = new javax.swing.GroupLayout(b4);
        b4.setLayout(b4Layout);
        b4Layout.setHorizontalGroup(
            b4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b4Layout.setVerticalGroup(
            b4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 40, 40));

        b5.setArcSize(15);
        b5.setCurrentColor(new java.awt.Color(255, 102, 102));
        b5.setSquarePadding(1.0);

        javax.swing.GroupLayout b5Layout = new javax.swing.GroupLayout(b5);
        b5.setLayout(b5Layout);
        b5Layout.setHorizontalGroup(
            b5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b5Layout.setVerticalGroup(
            b5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 40, 40));

        b10.setArcSize(15);
        b10.setCurrentColor(new java.awt.Color(251, 166, 82));
        b10.setSquarePadding(1.0);

        javax.swing.GroupLayout b10Layout = new javax.swing.GroupLayout(b10);
        b10.setLayout(b10Layout);
        b10Layout.setHorizontalGroup(
            b10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b10Layout.setVerticalGroup(
            b10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b10, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 40, 40));

        b7.setArcSize(15);
        b7.setCurrentColor(new java.awt.Color(102, 255, 204));
        b7.setSquarePadding(1.0);

        javax.swing.GroupLayout b7Layout = new javax.swing.GroupLayout(b7);
        b7.setLayout(b7Layout);
        b7Layout.setHorizontalGroup(
            b7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b7Layout.setVerticalGroup(
            b7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 40, 40));

        b8.setArcSize(15);
        b8.setCurrentColor(new java.awt.Color(153, 255, 153));
        b8.setSquarePadding(1.0);

        javax.swing.GroupLayout b8Layout = new javax.swing.GroupLayout(b8);
        b8.setLayout(b8Layout);
        b8Layout.setHorizontalGroup(
            b8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b8Layout.setVerticalGroup(
            b8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 40, 40));

        b9.setArcSize(15);
        b9.setCurrentColor(new java.awt.Color(255, 255, 153));
        b9.setSquarePadding(1.0);

        javax.swing.GroupLayout b9Layout = new javax.swing.GroupLayout(b9);
        b9.setLayout(b9Layout);
        b9Layout.setHorizontalGroup(
            b9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b9Layout.setVerticalGroup(
            b9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 40, 40));

        b6.setArcSize(15);
        b6.setCurrentColor(new java.awt.Color(255, 212, 148));
        b6.setSquarePadding(1.0);

        javax.swing.GroupLayout b6Layout = new javax.swing.GroupLayout(b6);
        b6.setLayout(b6Layout);
        b6Layout.setHorizontalGroup(
            b6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b6Layout.setVerticalGroup(
            b6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 40, 40));

        b11.setArcSize(15);
        b11.setCurrentColor(new java.awt.Color(114, 184, 255));
        b11.setSquarePadding(1.0);

        javax.swing.GroupLayout b11Layout = new javax.swing.GroupLayout(b11);
        b11.setLayout(b11Layout);
        b11Layout.setHorizontalGroup(
            b11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b11Layout.setVerticalGroup(
            b11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 40, 40));

        b12.setArcSize(15);
        b12.setCurrentColor(new java.awt.Color(204, 255, 204));
        b12.setSquarePadding(1.0);

        javax.swing.GroupLayout b12Layout = new javax.swing.GroupLayout(b12);
        b12.setLayout(b12Layout);
        b12Layout.setHorizontalGroup(
            b12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b12Layout.setVerticalGroup(
            b12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 40, 40));

        otherbg.setArcSize(15);
        otherbg.setSquarePadding(1.0);
        otherbg.setPreferredSize(new java.awt.Dimension(90, 40));
        otherbg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("More");
        otherbg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        mainContainer.add(otherbg, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 90, 40));

        recentContainer.setOpaque(false);
        recentContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        recentHeader.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        recentHeader.setForeground(new java.awt.Color(255, 255, 255));
        recentHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recentHeader.setText("Recent");
        recentContainer.add(recentHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 160, 30));

        rf1.setArcSize(15);
        rf1.setSquarePadding(1.0);

        javax.swing.GroupLayout rf1Layout = new javax.swing.GroupLayout(rf1);
        rf1.setLayout(rf1Layout);
        rf1Layout.setHorizontalGroup(
            rf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf1Layout.setVerticalGroup(
            rf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 40, 40));

        rf2.setArcSize(15);
        rf2.setSquarePadding(1.0);

        javax.swing.GroupLayout rf2Layout = new javax.swing.GroupLayout(rf2);
        rf2.setLayout(rf2Layout);
        rf2Layout.setHorizontalGroup(
            rf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf2Layout.setVerticalGroup(
            rf2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 40));

        rf3.setArcSize(15);
        rf3.setSquarePadding(1.0);

        javax.swing.GroupLayout rf3Layout = new javax.swing.GroupLayout(rf3);
        rf3.setLayout(rf3Layout);
        rf3Layout.setHorizontalGroup(
            rf3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf3Layout.setVerticalGroup(
            rf3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 40, 40));

        rf4.setArcSize(15);
        rf4.setSquarePadding(1.0);

        javax.swing.GroupLayout rf4Layout = new javax.swing.GroupLayout(rf4);
        rf4.setLayout(rf4Layout);
        rf4Layout.setHorizontalGroup(
            rf4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf4Layout.setVerticalGroup(
            rf4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 40, 40));

        rf5.setArcSize(15);
        rf5.setSquarePadding(1.0);

        javax.swing.GroupLayout rf5Layout = new javax.swing.GroupLayout(rf5);
        rf5.setLayout(rf5Layout);
        rf5Layout.setHorizontalGroup(
            rf5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf5Layout.setVerticalGroup(
            rf5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 40, 40));

        rf6.setArcSize(15);
        rf6.setSquarePadding(1.0);

        javax.swing.GroupLayout rf6Layout = new javax.swing.GroupLayout(rf6);
        rf6.setLayout(rf6Layout);
        rf6Layout.setHorizontalGroup(
            rf6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf6Layout.setVerticalGroup(
            rf6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 40, 40));

        rf7.setArcSize(15);
        rf7.setSquarePadding(1.0);

        javax.swing.GroupLayout rf7Layout = new javax.swing.GroupLayout(rf7);
        rf7.setLayout(rf7Layout);
        rf7Layout.setHorizontalGroup(
            rf7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf7Layout.setVerticalGroup(
            rf7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 40, 40));

        rf8.setArcSize(15);
        rf8.setSquarePadding(1.0);

        javax.swing.GroupLayout rf8Layout = new javax.swing.GroupLayout(rf8);
        rf8.setLayout(rf8Layout);
        rf8Layout.setHorizontalGroup(
            rf8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rf8Layout.setVerticalGroup(
            rf8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rf8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 40, 40));

        rb1.setArcSize(15);
        rb1.setSquarePadding(1.0);

        javax.swing.GroupLayout rb1Layout = new javax.swing.GroupLayout(rb1);
        rb1.setLayout(rb1Layout);
        rb1Layout.setHorizontalGroup(
            rb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb1Layout.setVerticalGroup(
            rb1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 40, 40));

        rb2.setArcSize(15);
        rb2.setSquarePadding(1.0);

        javax.swing.GroupLayout rb2Layout = new javax.swing.GroupLayout(rb2);
        rb2.setLayout(rb2Layout);
        rb2Layout.setHorizontalGroup(
            rb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb2Layout.setVerticalGroup(
            rb2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 40, 40));

        rb3.setArcSize(15);
        rb3.setSquarePadding(1.0);

        javax.swing.GroupLayout rb3Layout = new javax.swing.GroupLayout(rb3);
        rb3.setLayout(rb3Layout);
        rb3Layout.setHorizontalGroup(
            rb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb3Layout.setVerticalGroup(
            rb3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 40, 40));

        rb4.setArcSize(15);
        rb4.setSquarePadding(1.0);

        javax.swing.GroupLayout rb4Layout = new javax.swing.GroupLayout(rb4);
        rb4.setLayout(rb4Layout);
        rb4Layout.setHorizontalGroup(
            rb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb4Layout.setVerticalGroup(
            rb4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 40, 40));

        rb5.setArcSize(15);
        rb5.setSquarePadding(1.0);

        javax.swing.GroupLayout rb5Layout = new javax.swing.GroupLayout(rb5);
        rb5.setLayout(rb5Layout);
        rb5Layout.setHorizontalGroup(
            rb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb5Layout.setVerticalGroup(
            rb5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 40, 40));

        rb6.setArcSize(15);
        rb6.setSquarePadding(1.0);

        javax.swing.GroupLayout rb6Layout = new javax.swing.GroupLayout(rb6);
        rb6.setLayout(rb6Layout);
        rb6Layout.setHorizontalGroup(
            rb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb6Layout.setVerticalGroup(
            rb6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 40, 40));

        rb7.setArcSize(15);
        rb7.setSquarePadding(1.0);

        javax.swing.GroupLayout rb7Layout = new javax.swing.GroupLayout(rb7);
        rb7.setLayout(rb7Layout);
        rb7Layout.setHorizontalGroup(
            rb7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb7Layout.setVerticalGroup(
            rb7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 40, 40));

        rb8.setArcSize(15);
        rb8.setSquarePadding(1.0);

        javax.swing.GroupLayout rb8Layout = new javax.swing.GroupLayout(rb8);
        rb8.setLayout(rb8Layout);
        rb8Layout.setHorizontalGroup(
            rb8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        rb8Layout.setVerticalGroup(
            rb8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        recentContainer.add(rb8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 40, 40));

        mainContainer.add(recentContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 200, 260));

        previewHeader.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        previewHeader.setForeground(new java.awt.Color(255, 255, 255));
        previewHeader.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        previewHeader.setText("Preview");
        mainContainer.add(previewHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 80, 30));

        sep1.setArcSize(5);

        javax.swing.GroupLayout sep1Layout = new javax.swing.GroupLayout(sep1);
        sep1.setLayout(sep1Layout);
        sep1Layout.setHorizontalGroup(
            sep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        sep1Layout.setVerticalGroup(
            sep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        mainContainer.add(sep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 375, 650, 5));

        previewScroll.setBorder(null);

        previewTextPane.setEditable(false);
        previewTextPane.setBackground(new java.awt.Color(39, 52, 65));
        previewTextPane.setBorder(null);
        previewTextPane.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        previewTextPane.setForeground(new java.awt.Color(255, 255, 255));
        previewTextPane.setText("The quick brown fox jumps over the lazy dog");
        previewScroll.setViewportView(previewTextPane);

        mainContainer.add(previewScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 400, 530, 50));

        sep2.setArcSize(5);

        javax.swing.GroupLayout sep2Layout = new javax.swing.GroupLayout(sep2);
        sep2.setLayout(sep2Layout);
        sep2Layout.setHorizontalGroup(
            sep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        sep2Layout.setVerticalGroup(
            sep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        mainContainer.add(sep2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 465, 730, 5));

        cancelButton.setText("CANCEL");
        cancelButton.setArcSize(15);
        cancelButton.setCurrentColor(new java.awt.Color(15, 20, 25));
        mainContainer.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 110, -1));

        saveButton.setText("SAVE");
        saveButton.setArcSize(15);
        saveButton.setCurrentColor(new java.awt.Color(15, 20, 25));
        mainContainer.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 110, -1));

        try {
            noBG.set_SVG_URL_String("/icons/colorpick/no-color-normal.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        noBG.setArcSize(15);
        noBG.setCurrentColor(new java.awt.Color(0, 0, 0));
        noBG.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        noBG.setIconSquarePadding(3.0);
        mainContainer.add(noBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, 40, 40));

        b13.setArcSize(15);
        b13.setSquarePadding(1.0);

        javax.swing.GroupLayout b13Layout = new javax.swing.GroupLayout(b13);
        b13.setLayout(b13Layout);
        b13Layout.setHorizontalGroup(
            b13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        b13Layout.setVerticalGroup(
            b13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(b13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 40, 40));

        getContentPane().add(mainContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.RoundedPanel b1;
    private frontend.RoundedPanel b10;
    private frontend.RoundedPanel b11;
    private frontend.RoundedPanel b12;
    private frontend.RoundedPanel b13;
    private frontend.RoundedPanel b2;
    private frontend.RoundedPanel b3;
    private frontend.RoundedPanel b4;
    private frontend.RoundedPanel b5;
    private frontend.RoundedPanel b6;
    private frontend.RoundedPanel b7;
    private frontend.RoundedPanel b8;
    private frontend.RoundedPanel b9;
    private javax.swing.JLabel backgroundHeader;
    private frontend.RoundedButton cancelButton;
    private frontend.RoundedPanel f1;
    private frontend.RoundedPanel f10;
    private frontend.RoundedPanel f11;
    private frontend.RoundedPanel f12;
    private frontend.RoundedPanel f13;
    private frontend.RoundedPanel f2;
    private frontend.RoundedPanel f3;
    private frontend.RoundedPanel f4;
    private frontend.RoundedPanel f5;
    private frontend.RoundedPanel f6;
    private frontend.RoundedPanel f7;
    private frontend.RoundedPanel f8;
    private frontend.RoundedPanel f9;
    private javax.swing.JLabel foregroundHeader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainContainer;
    private frontend.SVGIconPanel noBG;
    private frontend.RoundedPanel otherbg;
    private frontend.RoundedPanel otherfg;
    private javax.swing.JLabel previewHeader;
    private javax.swing.JScrollPane previewScroll;
    private javax.swing.JTextPane previewTextPane;
    private frontend.RoundedPanel rb1;
    private frontend.RoundedPanel rb2;
    private frontend.RoundedPanel rb3;
    private frontend.RoundedPanel rb4;
    private frontend.RoundedPanel rb5;
    private frontend.RoundedPanel rb6;
    private frontend.RoundedPanel rb7;
    private frontend.RoundedPanel rb8;
    private javax.swing.JPanel recentContainer;
    private javax.swing.JLabel recentHeader;
    private frontend.RoundedPanel rf1;
    private frontend.RoundedPanel rf2;
    private frontend.RoundedPanel rf3;
    private frontend.RoundedPanel rf4;
    private frontend.RoundedPanel rf5;
    private frontend.RoundedPanel rf6;
    private frontend.RoundedPanel rf7;
    private frontend.RoundedPanel rf8;
    private frontend.RoundedButton saveButton;
    private frontend.RoundedPanel sep1;
    private frontend.RoundedPanel sep2;
    // End of variables declaration//GEN-END:variables
}
