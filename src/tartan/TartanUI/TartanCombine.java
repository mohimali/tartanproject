package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import tartan.Tartan;
import tartan.combination.*;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

import static javax.swing.ScrollPaneConstants.*;

/**
 * Created by Mohim on 06/03/2015.
 * http://stackoverflow.com/questions/28922000/how-to-make-all-of-the-region-of-a-jpanel-draggable-including-a-jsvgcanvas-withi
 * http://softwareisart.blogspot.co.uk/2011/11/drag-and-drop-of-complex-custom-objects.html
 */
public class TartanCombine extends JPanel {

    JLabel lblNew = new JLabel("<html><font color='white'><b>Here you can choose two tartans to combine</b></font></html>");
    JLabel lblEquals;
    JPanel allTartans;
    ArrayList<JLayer> arrayOfTartansForListeners = new ArrayList<JLayer>();

    String[] combinationAlgorithms = new String[EnumSet.allOf(OPERATION_BINARY.class).size()];

    JComboBox combinationList;

    ArrayList<TartanSingle> tsArray = new ArrayList<TartanSingle>();
    JButton btnRefreshTartanList, btnCombineTartan, btnSaveResultTartan;
    int noOfTartansWidth = 4;

    JLayer tartanResult;
    JLayer tartanFirstChoice;
    JLayer tartanSecondChoice;

    JScrollPane jScrollPane;

    ButtonGroup bgCombinationMode = new ButtonGroup();
    JRadioButton jrUnaryOperations = new JRadioButton("<html><font color='white'><b> Unary Operation </b></font></html>");
    JRadioButton jrBinaryOperations = new JRadioButton("<html><font color='white'><b> Binary Operation </b></font></html>\"");


    private static int NON_DROP_REGION = 0;
    private static int DROP_REGION = 1;
    int large = 260;
    int largeOfset = large + 20;
    int medium = 160;
    int mediumOfset = medium + 20;
    int small = 100;
    int smallOfset = small + 20;

    String tempMedium = mediumOfset + ":" + mediumOfset + ":" + mediumOfset;
    String tempSmall = smallOfset + ":" + smallOfset + ":" + smallOfset;
    String tempLarge = largeOfset + ":" + largeOfset + ":" + largeOfset;

    String mediumString = "width " + tempMedium + ",height " + tempMedium;
    String mediumForButtonString = "width " + "75:75:75" + ",height " + "75:75:75";
    String smallString = "width " + tempSmall + ",height " + tempSmall + "";
    String largeString = "width " + tempLarge + ",height " + tempLarge + "";

    DataFlavor dataFlavor = new DataFlavor(Tartan.class,
            Tartan.class.getSimpleName());

    String currentCombination = "";
    int combinationMode = 1; //0 is unary, 1 is binary

    public void init() {

        jrUnaryOperations.setActionCommand("UNARY");
        jrBinaryOperations.setActionCommand("BINARY");

        bgCombinationMode.add(jrUnaryOperations);
        bgCombinationMode.add(jrBinaryOperations);
        jrBinaryOperations.setSelected(true);

        int index = 0;
        for (OPERATION_BINARY text : EnumSet.allOf(OPERATION_BINARY.class)) {
            combinationAlgorithms[index] = text.toString();
            //System.out.println(text);
            //System.out.println(combinationAlgorithms[index]);
            index++;
        }


        combinationList = new JComboBox(combinationAlgorithms);
        combinationList.setSelectedIndex(0);
        combinationList.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox type = (JComboBox) e.getSource();
                String itemType = (String) type.getSelectedItem();
                System.out.println(itemType);
            }
        });


        allTartans = new JPanel(new MigLayout(""));

        Tartan blankLargeTartan = new Tartan(large, 1);
        Tartan blankMediumTartan = new Tartan(medium, 1);
        Tartan blankSmallTartan = new Tartan(small, 1);

        blankLargeTartan.addThread(Color.GRAY, 1, "Grey", "N");
        blankMediumTartan.addThread(Color.GRAY, 1, "Grey", "N");
        blankSmallTartan.addThread(Color.GRAY, 1, "Grey", "N");


        tartanFirstChoice = createLayer(997, blankMediumTartan, medium, DROP_REGION);
        tartanSecondChoice = createLayer(998, blankMediumTartan, medium, DROP_REGION);
        tartanResult = createLayer(999, blankLargeTartan, large, NON_DROP_REGION);

        btnRefreshTartanList = new JButton("<html><font color='white'>Refresh Tartans</font></html>");
        ImageIcon plus = new ImageIcon(this.getClass().getResource("resources/images/plus.png"));
        ImageIcon equals = new ImageIcon(this.getClass().getResource("resources/images/equals.png"));

        lblEquals = new JLabel(equals);
        btnCombineTartan = new JButton(plus);
        btnCombineTartan.setPreferredSize(new Dimension(75, 75));
        lblEquals.setPreferredSize(new Dimension(75, 75));
        btnCombineTartan.setBackground(Color.DARK_GRAY);
        allTartans.setBackground(Color.darkGray);
        allTartans.setPreferredSize(new Dimension(555, 250));
        btnSaveResultTartan = new JButton("<html><font color='black'>Save new Tartan</font></html>");
        jScrollPane = new JScrollPane(allTartans);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);


    }

    public TartanCombine() {
        this.setLayout(new MigLayout(""));
        init();
        String width = ",width 570:570:570";
        String height = ",height 220:220:220";

        String width2 = ",width 300:300:300";
        String height2 = ",height 30:30:30";

        String spanMedium = ", span 1 2";

        this.setBackground(Color.darkGray);
        this.add(jrUnaryOperations, "span 4");
        this.add(btnSaveResultTartan, "align center, span 2 2,wrap");
        this.add(jrBinaryOperations, "Wrap");

        //this.add(lblNew, "Wrap,span, dock south");
        //this.add(btnRefreshTartanList, "Wrap,dock south");

        JPanel topPanel = new JPanel(new MigLayout(""));
        topPanel.setBackground(Color.darkGray);
        //topPanel.setPreferredSize(new Dimension(150, 150));
        topPanel.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(tartanFirstChoice, mediumString);

        JPanel combinationStuff = new JPanel(new MigLayout(""));
        combinationStuff.setBackground(Color.darkGray);
        combinationStuff.add(combinationList, "wrap" + width2 + height2);
        combinationStuff.add(btnCombineTartan, "align center");

        topPanel.add(combinationStuff);
        topPanel.add(tartanSecondChoice, mediumString);
        topPanel.add(lblEquals);
        this.add(topPanel, "span 4");
        this.add(tartanResult, "wrap , " + largeString + ",top, span 2 2");
        this.add(jScrollPane, "span 4,align left" + width + height + "");


    }

    public void populateAllTartans(ArrayList<Tartan> tartansList) {

        String wrap = ",wrap";
        allTartans.removeAll();
        arrayOfTartansForListeners = new ArrayList<JLayer>(); // RESET

        allTartans.repaint();
        for (int index = 0; index < tartansList.size(); index++) {
            Tartan myTartan = new Tartan(tartansList.get(index).getThreadList(),
                    tartansList.get(index).getSettCount(), small, true);

            //System.out.println("adding the followoing: " + myTartan.toString());
            myTartan.updateDimensions(small);
            JLayer currentMiniTartan = createLayer(index, myTartan, small, NON_DROP_REGION);

            // DO A NEW ROW IF NEEDED
            if (((index + 1) % noOfTartansWidth) == 0) {
                allTartans.add(currentMiniTartan, smallString + wrap);
                arrayOfTartansForListeners.add(currentMiniTartan);
            } else {
                allTartans.add(currentMiniTartan, smallString);
                arrayOfTartansForListeners.add(currentMiniTartan);
            }
        } // outside for
        allTartans.repaint();
        allTartans.validate();
        allTartans.updateUI();


    }


    public void addRefreshTartansList(ActionListener refreshTartansList) {
        btnRefreshTartanList.addActionListener(refreshTartansList);
    }

    public void addCombineTartansListener(ActionListener combineTartansListener) {
        btnCombineTartan.addActionListener(combineTartansListener);
    }

    public void addUpdateCombinationModeListener(ActionListener updateCombinationModeUnary,
                                                 ActionListener updateCombinationModeBinary) {
        jrUnaryOperations.addActionListener(updateCombinationModeUnary);
        jrBinaryOperations.addActionListener(updateCombinationModeBinary);
    }


    public void showUnaryOperations(boolean showUnaryOperations) {
        if (showUnaryOperations) // UNARY
        {
            combinationMode = 0; // UNARY OPERATIONS ALLOWED
            // POPULATE WITH UNARY OPERATIONS
            combinationAlgorithms = new String[EnumSet.allOf(OPERATION_UNARY.class).size()];
            int index = 0;
            for (OPERATION_UNARY text : EnumSet.allOf(OPERATION_UNARY.class)) {
                combinationAlgorithms[index] = text.toString();
                index++;
            }

            combinationList.setModel(new DefaultComboBoxModel(combinationAlgorithms));
            combinationList.setSelectedIndex(0);
            combinationList.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JComboBox type = (JComboBox) e.getSource();
                    String itemType = (String) type.getSelectedItem();
                }
            });


            tartanSecondChoice.setVisible(false);
        } else if (!showUnaryOperations) //BINARY
        {
            combinationMode = 1; // BINARY OPERATIONS ALLOWED
            // populate with binary ops
            combinationAlgorithms = new String[EnumSet.allOf(OPERATION_BINARY.class).size()];
            //System.out.println("Inside binary");
            int index = 0;
            for (OPERATION_BINARY text : EnumSet.allOf(OPERATION_BINARY.class)) {
                combinationAlgorithms[index] = text.toString();
                index++;
            }

            combinationList.setModel(new DefaultComboBoxModel(combinationAlgorithms));
            combinationList.setSelectedIndex(0);
            combinationList.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JComboBox type = (JComboBox) e.getSource();
                    String itemType = (String) type.getSelectedItem();
                }
            });
            tartanSecondChoice.setVisible(true);
        } // elseif for BINARY

    } // show

    public int getCombinationMode() {
        return combinationMode;
    }

    public Tartan getFirstTartan() {
        TartanSingle t = (TartanSingle) tartanFirstChoice.getView();
        return t.getTartan();
    } //getFirstTartan

    public Tartan getSecondTartan() {
        TartanSingle t = (TartanSingle) tartanSecondChoice.getView();
        return t.getTartan();
    } //getSecondTartan

    public OPERATION_BINARY getOperationBinaryMode() {
        int index = combinationList.getSelectedIndex();
        String operation = combinationList.getSelectedItem().toString();

        OPERATION_BINARY currentOp = null;

        for (OPERATION_BINARY text : EnumSet.allOf(OPERATION_BINARY.class)) {
            if (text.toString() == operation) {
                currentOp = text;
            }
        }

        return currentOp;
    }

    public OPERATION_UNARY getOperationUnaryMode() {
        int index = combinationList.getSelectedIndex();
        String operation = combinationList.getSelectedItem().toString();

        OPERATION_UNARY currentOp = null;

        for (OPERATION_UNARY text : EnumSet.allOf(OPERATION_UNARY.class)) {
            if (text.toString() == operation) {
                currentOp = text;
            }
        }

        return currentOp;
    }

    public void updateResultTartan(Tartan newResultTartan) {
        TartanSingle ts = (TartanSingle) tartanResult.getView();
        newResultTartan.updateDimensions(large);
        ts.addTartanToDisplay(newResultTartan);
        ts.validate();

    }

    public void addDoubleClickListenerToFirstChoice(MouseListener doubleClickLoadTartanListener) {
        tartanFirstChoice.addMouseListener(doubleClickLoadTartanListener);
        TartanSingle myTS = (TartanSingle) tartanFirstChoice.getView();
        myTS.canvas.addMouseListener(doubleClickLoadTartanListener);
    }

    public void addDoubleClickListenerToSecondChoice(MouseListener doubleClickLoadTartanListener) {
        tartanSecondChoice.addMouseListener(doubleClickLoadTartanListener);
        TartanSingle myTS = (TartanSingle) tartanSecondChoice.getView();
        myTS.canvas.addMouseListener(doubleClickLoadTartanListener);
    }

    public void addDoubleClickListenerToResult(MouseListener doubleClickLoadTartanListener) {
        tartanResult.addMouseListener(doubleClickLoadTartanListener);
        TartanSingle myTS = (TartanSingle) tartanResult.getView();
        myTS.canvas.addMouseListener(doubleClickLoadTartanListener);
    }

    public void addDoubleClickListenerToIndexedMini(int i, MouseListener doubleClickLoadTartanListener) {
        JLayer currentMiniT = arrayOfTartansForListeners.get(i);
        //currentMiniT.addMouseListener(doubleClickLoadTartanListener);
        TartanSingle myTS = (TartanSingle) currentMiniT.getView();
        myTS.canvas.addMouseListener(doubleClickLoadTartanListener);
    }

    public void addSaveTartanResultListener(ActionListener saveTartanResultListener) {
        btnSaveResultTartan.addActionListener(saveTartanResultListener);
    }

    public Tartan getCombinationResultTartan() {
        TartanSingle tss = (TartanSingle) tartanResult.getView();
        return tss.getTartan();
    }


    class TransferableTartan implements Transferable {

        private Tartan tartan;

        public TransferableTartan(Tartan ani) {

            this.tartan = ani;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{dataFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(dataFlavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException, IOException {

            if (flavor.equals(dataFlavor))
                return tartan;
            else
                throw new UnsupportedFlavorException(flavor);
        }
    } //TransferableTartan

    class DragGestureForJLayer implements DragGestureListener {

        DragGestureForJLayer() {

        }

        @Override
        public void dragGestureRecognized(DragGestureEvent event) {

            //System.out.println("triggered outside");
            Cursor cursor = null;
            JLayer myTartanX = (JLayer) event.getComponent();
            TartanSingle myTartan = (TartanSingle) myTartanX.getView();

            // System.out.println("About to print");
            //System.out.println(myTartan.getTartan().toString());

            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }
            Tartan tartan = myTartan.getTartan();

            event.startDrag(cursor, new TransferableTartan(tartan));
        }


    } //DragGestureListImp

    class DragGestureForCanvas implements DragGestureListener {

        JLayer currentLayer;

        DragGestureForCanvas(JLayer aa) {
            currentLayer = aa;
        }

        @Override
        public void dragGestureRecognized(DragGestureEvent event) {
            Cursor cursor = null;
            JLayer myTartanX = currentLayer;
            TartanSingle myTartan = (TartanSingle) myTartanX.getView();
            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }
            Tartan tartan = myTartan.getTartan();
            tartan.updateDimensions(medium);
            event.startDrag(cursor, new TransferableTartan(tartan));
        }


    } //DragGestureListImp

    class MyDropTargetListImp extends DropTargetAdapter implements
            DropTargetListener {

        private DropTarget dropTarget;
        private JLayer customPanel;

        public MyDropTargetListImp(JLayer dropPanel) {
            this.customPanel = dropPanel;

            dropTarget = new DropTarget(dropPanel, DnDConstants.ACTION_COPY, this,
                    true, null);
        }


        public void drop(DropTargetDropEvent event) {
            try {

                //System.out.println("dropped Event");
                Transferable tr = event.getTransferable();
                Tartan an = (Tartan) tr.getTransferData(dataFlavor);
                //System.out.println("aa" + an);
                if (event.isDataFlavorSupported(dataFlavor)) {
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    //this.customPanel.addTartanToDisplay(an);
                    TartanSingle aa = (TartanSingle) this.customPanel.getView();
                    aa.addTartanToDisplay(an);
                    event.dropComplete(true);
                    this.customPanel.validate();
                    return;
                }
                event.rejectDrop();
            } catch (Exception e) {
                e.printStackTrace();
                event.rejectDrop();
            }
        } //drop
    } //MyDropTargetListImp

    private JLayer<JComponent> createLayer(int index, Tartan tartanInserted, int layerSize, int type) {
        tartanInserted.updateDimensions(layerSize);

        final LayerUI<JComponent> layerUI;

        layerUI = new LayerUI<JComponent>() {

            public void installUI(JComponent c) {
                super.installUI(c);
                ((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK);
            }

            public void uninstallUI(JComponent c) {
                super.uninstallUI(c);
                // reset the layer event mask
                ((JLayer) c).setLayerEventMask(0);
            }

            public void eventDispatched(AWTEvent e, JLayer<? extends JComponent> l) {
                if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
                }
            }


        }; //layerUI


        DragSource ds = new DragSource();

        TartanSingle ts = new TartanSingle(index, tartanInserted);

        JLayer tempLayerUI = new JLayer<JComponent>(ts, layerUI);

        ds.createDefaultDragGestureRecognizer(ts.canvas,
                DnDConstants.ACTION_COPY, new DragGestureForCanvas(tempLayerUI));

        ds.createDefaultDragGestureRecognizer(tempLayerUI,
                DnDConstants.ACTION_COPY, new DragGestureForJLayer());

        // MAKE IT DROPPABLE IF ITS A DROP REGION
        if (type == DROP_REGION) {
            new MyDropTargetListImp(tempLayerUI);
        }

        setBorder(BorderFactory.createEmptyBorder());

        return tempLayerUI;
    }

}
