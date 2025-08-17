/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.ColorScheme;
import book.bookpicker.Book;
import book.bookpicker.BookConstructor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.foreign.AddressLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;
import legacy.AfterEventCode;

/**
 *
 * @author BRIN
 */
public class bookPickerController {
    
    private Color activeColor = new Color(27,36,45);
    
    private int currentYear = 1;
    
    private bookPickerFrame bpf;
    
    // **** DECLARATION OF GUI VARIABLES **** //
    
    private RoundedButton[] selectionButtons;
    private JLabel leftRotate;
    private JLabel rightRotate;
    private JLabel ordinalNumber;
    private JLabel ordinalIndicator;
    private JLabel yearLabel;
    
    // ********* END OF DECLARATION ********* //
    
    
    private Map<RoundedButton, Book> rdbBook;
    private RoundedButton[] workingSelectionButtons;
    
    private Map<RoundedButton, MouseAdapter> rdbMAdaptMap;
    private Map<RoundedButton, ActionListener> rdbActListenMap;
    
    
    private bookPickerController(bookPickerFrame bpf){
        
        // *** INITIALIZATION *** //
        this.bpf = bpf;
        this.selectionButtons = bpf.getSelectionButtonArray();
        this.leftRotate = bpf.getLeftRotateLabel();
        this.rightRotate = bpf.getRightRotateLabel();
        this.ordinalNumber = bpf.getOrdinalNumberLabel();
        this.ordinalIndicator = bpf.getOrdinalIndicatorLabel();
        this.yearLabel = bpf.getYearLabel();
        
        // *** END OF INIT *** //
        
        rdbMAdaptMap = new LinkedHashMap<>();
        rdbActListenMap = new LinkedHashMap<>();
        rdbBook = new LinkedHashMap<>();
        workingSelectionButtons = new RoundedButton[0];
        addNavigationFunctionality();
        initializeMaps(currentYear);
    }
    
    public static void initializeController(bookPickerFrame bpf){
        bookPickerController bookPickerController = new bookPickerController(bpf);
    }
    
    
    
    private void initializeMaps(int year){
        
        List<Book> bookList = BookConstructor.getBooksByYear(year);
        int size = bookList.size();
        workingSelectionButtons = new RoundedButton[size];
        int i = 0;
        
        for ( ; i < Math.min(selectionButtons.length, size); i++){
        
            rdbBook.put(selectionButtons[i], bookList.get(i));
            workingSelectionButtons[i] = selectionButtons[i];
        }
        
        for(; i < selectionButtons.length; i++){
            
            selectionButtons[i].setEnabled(false);
            selectionButtons[i].setText("-");
        }
        
        for(RoundedButton rdb : workingSelectionButtons){
            
            addButtonFunctionality(rdb, rdbBook);
            
        }
    }
    
    private void removePreviousConfiguration(){
        
        for(RoundedButton rdb : workingSelectionButtons){
            
            rdb.removeMouseListener(rdbMAdaptMap.get(rdb));
            rdb.removeActionListener(rdbActListenMap.get(rdb));
            
        }
        
        rdbBook = new LinkedHashMap<>();
        rdbMAdaptMap = new LinkedHashMap<>();
        rdbActListenMap = new LinkedHashMap<>();
        workingSelectionButtons = new RoundedButton[0];
    }
    
    private void addButtonFunctionality(RoundedButton rdb, Map<RoundedButton, Book> rdbMap){
        Book book = rdbMap.get(rdb);
        AfterEventCode aec = book.getActionCode();
        Color[] cs = book.getColorScheme();
        
        
        rdb.setText(book.getShortHandTitle());
        rdb.setCurrentColor(cs[0]);
        
        MouseAdapter selectionMAdapt = new MouseAdapter() {
        
            @Override
            public void mouseEntered(MouseEvent event){
                rdb.setCurrentColor(cs[1]);
            }

            @Override
            public void mouseExited(MouseEvent event){
                rdb.setCurrentColor(cs[0]);
            }

            @Override
            public void mousePressed(MouseEvent event){
                rdb.setCurrentColor(cs[2]);
            }

            @Override
            public void mouseReleased(MouseEvent event){
                rdb.setCurrentColor(cs[1]);
            }
        };
        
        ActionListener selectionActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                aec.start();
            }
        };
        
        
        rdbActListenMap.put(rdb, selectionActListen);
        rdb.addActionListener(selectionActListen);
        rdbMAdaptMap.put(rdb, selectionMAdapt);
        rdb.addMouseListener(selectionMAdapt);
        rdb.setEnabled(true);
    }
    
    private ColorScheme navCS = new ColorScheme(new Color(215,215,215), new Color(231,231,231), new Color(255,255,255), new Color(153,153,153));
    
    private void addNavigationFunctionality(){
        
        
        MouseAdapter NavMAdapt = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event){
                leftRotate.setForeground(navCS.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                leftRotate.setForeground(navCS.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent event){
                leftRotate.setForeground(navCS.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                leftRotate.setForeground(navCS.getHoverColor());
            }
            
            @Override
            public void mouseClicked(MouseEvent event){
                currentYear--;
                if(currentYear < 1){
                    currentYear = 4;
                }
                setOrdinal(ordinalNumber, ordinalIndicator, currentYear);
                removePreviousConfiguration();
                initializeMaps(currentYear);
                        
            }
        };
        
        
        leftRotate.addMouseListener(NavMAdapt);
        
        NavMAdapt = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event){
                rightRotate.setForeground(navCS.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                rightRotate.setForeground(navCS.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent event){
                rightRotate.setForeground(navCS.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                rightRotate.setForeground(navCS.getHoverColor());
            }
            
            @Override
            public void mouseClicked(MouseEvent event){
                
                currentYear++;
                if(currentYear > 4){
                    currentYear = 1;
                }
                setOrdinal(ordinalNumber, ordinalIndicator, currentYear);
                removePreviousConfiguration();
                initializeMaps(currentYear);
            }
        };
        
        rightRotate.addMouseListener(NavMAdapt);
        
    }
    
    private void setOrdinal(JLabel numberLabel, JLabel indicatorLabel, int number){
        
        String numberString = Integer.toString(number);
        String indicator = "";
        
        if(numberString.endsWith("1")){
            indicator = "st";
        }
        else if(numberString.endsWith("2")){
            indicator = "nd";
        }
        else if(numberString.endsWith("3")){
            indicator = "rd";
        }
        else{
            indicator = "th";
        }
        
        numberLabel.setText(numberString);
        indicatorLabel.setText(indicator);
    }
}
