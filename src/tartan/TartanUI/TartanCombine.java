package tartan.TartanUI;

import javafx.scene.input.MouseDragEvent;
import net.miginfocom.swing.MigLayout;
import tartan.Tartan;
import tartan.TartanThread;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mohim on 06/03/2015.
 */
public class TartanCombine extends JPanel {

    JLabel lblNew = new JLabel("<html><font color='white'><b>Here you can choose two tartans to combine</b></font></html>");
    JPanel allTartans;

    String[] combinationAlgorithms = { "Algorithm 1", "Algorithm 2", "Algorithm 3", "Algorithm 4", "Algorithm 5" };

    //Create the combo box, select item at index 4.
//Indices start at 0, so 4 specifies the pig.
    JComboBox combinationList;

    ArrayList<TartanSingle> tsArray = new ArrayList<TartanSingle>();
    JButton btnRefreshTartanList,btnCombineTartan;
    int noOfTartansWidth = 4;

    JLayer tartanResult;
    JLayer tartanFirstChoice;
    JLayer tartanSecondChoice;

    JScrollPane jScrollPane;

    private static int NON_DROP_REGION = 0;
    private static int DROP_REGION = 1;
    int large = 330;
    int largeOfset = large + 20;
    int medium = 150;
    int mediumOfset = medium + 20;
    int small = 100;
    int smallOfset = small + 20;

    String tempMedium = mediumOfset + ":" + mediumOfset + ":"  + mediumOfset;
    String tempSmall =  smallOfset + ":" + smallOfset + ":"  + smallOfset;
    String tempLarge =  largeOfset + ":" + largeOfset + ":"  + largeOfset;

    String mediumString = "width " + tempMedium + ",height " + tempMedium;
    String mediumForButtonString = "width " + "75:75:75" + ",height " + "75:75:75";
    String smallString = "width " + tempSmall + ",height " + tempSmall + "";
    String largeString = "width " + tempLarge + ",height " + tempLarge + "";

    DataFlavor dataFlavor = new DataFlavor(Tartan.class,
            Tartan.class.getSimpleName());

    String currentCombination = "";


    public void init() {

        combinationList = new JComboBox(combinationAlgorithms);
        combinationList.setSelectedIndex(4);
        combinationList.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox jcmbType = (JComboBox) e.getSource();
                String cmbType = (String) jcmbType.getSelectedItem();
                System.out.println(cmbType);
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
        btnCombineTartan = new JButton(plus);
        btnCombineTartan.setPreferredSize(new Dimension(75,75));
        btnCombineTartan.setBackground(Color.DARK_GRAY);
        allTartans.setBackground(Color.darkGray);
        allTartans.setPreferredSize(new Dimension(555, 250));

        jScrollPane = new JScrollPane(allTartans);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(7);





    }

    public TartanCombine() {
        this.setLayout(new MigLayout(""));
        init();
        String width = ",width 600:600:600";
        String height = ",height 220:220:220";

        String width2 = ",width 180:180:180";
        String height2 = ",height 30:30:30";

        String spanMedium = ", span 1 2";

        this.setBackground(Color.darkGray);
        //this.add(lblNew, "Wrap,span, dock south");
        //this.add(btnRefreshTartanList, "Wrap,dock south");


        JPanel addPanel = new JPanel(new MigLayout(""));
        addPanel.setPreferredSize(new Dimension(150, 150));
        addPanel.setBorder(BorderFactory.createEmptyBorder());

        JPanel leftPanel = new JPanel(new MigLayout(""));
        //leftPanel.setPreferredSize(new Dimension(150, 150));
        leftPanel.setBorder(BorderFactory.createEmptyBorder());
        leftPanel.add(tartanFirstChoice);
        leftPanel.add(combinationList);
        leftPanel.add(btnCombineTartan);

        //this.add(tartanFirstChoice,mediumString + spanMedium);
        //addPanel.add(combinationList, "wrap" + width2 + height2);
        //addPanel.add(btnCombineTartan, "wrap," + mediumForButtonString);
        //this.add(addPanel,"wrap" + ",span 1 2");
        this.add(tartanSecondChoice, mediumString + ",span 1 2" + "");
        //this.add(tartanResult, largeString + ",wrap");
        this.add(jScrollPane,"span 3,align left"+width+height + "");







    }

    public void populateAllTartans(ArrayList<Tartan> tartansList) {

        String wrap = ",wrap";
        allTartans.removeAll();
        allTartans.validate();
        allTartans.repaint();
        for (int index = 0; index < tartansList.size(); index++) {
            Tartan myTartan = new Tartan(tartansList.get(index).getThreadList(),
                    tartansList.get(index).getSettCount(), small, true);
            myTartan.updateDimensions(small);
            JLayer currentMiniTartan = createLayer(index, myTartan, small, NON_DROP_REGION);

            // DO A NEW ROW IF NEEDED
            if (((index + 1) % noOfTartansWidth) == 0) {
                allTartans.add(currentMiniTartan, smallString + wrap);
            } else {
                allTartans.add(currentMiniTartan, smallString);
            }
        } // outside for
        allTartans.repaint();
        allTartans.updateUI();
    }


    public void addRefreshTartansList(ActionListener refreshTartansList) {
        btnRefreshTartanList.addActionListener(refreshTartansList);
    }

    public void addCombineTartansListener(ActionListener combineTartansListener) {
        btnCombineTartan.addActionListener(combineTartansListener);
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

    class DragGestureListImp implements DragGestureListener {

        DragGestureListImp() {

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

    class DragGestureListImp1 implements DragGestureListener {

        JLayer currentLayer;

        DragGestureListImp1(JLayer aa) {
            currentLayer = aa;
        }

        @Override
        public void dragGestureRecognized(DragGestureEvent event) {

            //System.out.println("triggered inside canvas method");
            Cursor cursor = null;
            JLayer myTartanX = currentLayer;
            TartanSingle myTartan = (TartanSingle) myTartanX.getView();

            // System.out.println("About to print canvas");
            //System.out.println(myTartan.getTartan().toString());

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
        private JLayer panel;

        public MyDropTargetListImp(JLayer panel) {
            this.panel = panel;

            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this,
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

                    //this.panel.addTartanToDisplay(an);
                    TartanSingle aa = (TartanSingle) this.panel.getView();
                    aa.addTartanToDisplay(an);
                    event.dropComplete(true);
                    this.panel.validate();
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
        // This custom layerUI will intercept all mouseMotion events generated within its borders and delegate to the wrapped JPanel

        final LayerUI<JComponent> layerUI;

        layerUI = new LayerUI<JComponent>() {

            public void installUI(JComponent c) {
                super.installUI(c);
                // enable mouse events for the layer's subcomponents
                //((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK);
                ((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK);
            }

            public void uninstallUI(JComponent c) {
                super.uninstallUI(c);
                // reset the layer event mask
                ((JLayer) c).setLayerEventMask(0);
            }


            // overridden method which catches MouseMotion events
            public void eventDispatched(AWTEvent e, JLayer<? extends JComponent> l) {
                if (e.getID() == MouseEvent.MOUSE_DRAGGED) {

                    //e.startDrag(cursor, new TransferableTartan(tartan));
                    // Do drag gesture processing here.
                    // Note, you cannot dispatch these events to the view component, since that
                    // creates an event loop.
                }


            }


        }; //layerUI


        DragSource ds = new DragSource();
        // create a component to be decorated with the layer
        // This would be your custom component.
        TartanSingle customPanel = new TartanSingle(index, tartanInserted);
        //customPanel.add(new JButton("JButton"));

        JLayer tempLayerUI = new JLayer<JComponent>(customPanel, layerUI);

        ds.createDefaultDragGestureRecognizer(customPanel.canvas,
                DnDConstants.ACTION_COPY, new DragGestureListImp1(tempLayerUI));

        ds.createDefaultDragGestureRecognizer(tempLayerUI,
                DnDConstants.ACTION_COPY, new DragGestureListImp());

        // MAKE IT DROPPABLE IF ITS A DROP REGION
        if (type == DROP_REGION) {
            new MyDropTargetListImp(tempLayerUI);
        }

        setBorder(BorderFactory.createEmptyBorder());
        // create the layer for the panel using our custom layerUI
        return tempLayerUI;
    }

}
