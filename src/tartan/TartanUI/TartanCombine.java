package tartan.TartanUI;

import javafx.scene.input.MouseDragEvent;
import net.miginfocom.swing.MigLayout;
import tartan.Tartan;

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

    JLabel lblNew = new JLabel("Here you can choose two tartans to combine");
    JLabel lblPlus;

    JPanel allTartans;
    ArrayList<TartanSingle> tsArray = new ArrayList<TartanSingle>();
    JButton btnRefreshTartanList;
    int noOfTartansWidth = 4;


    TartanSingle tartanSingle, tartanSingle1, tartanSingle2;
    TartanSingle tartanResult;
    JLayer tartanFirstChoice;
    JLayer tartanSecondChoice;


    int large = 300;
    int largeOfset = large;

    int medium = 150;
    int mediumOfset = medium + 20;

    int small = 100;
    int smallOfset = small + 20;

    String mediumString = "width " + mediumOfset + ",height " + mediumOfset;
    String smallString = "width " + smallOfset + ",height " + smallOfset + "";

    DataFlavor dataFlavor = new DataFlavor(Tartan.class,
            Tartan.class.getSimpleName());

    private void setUpDragNDrop() {
//        DragSource ds = new DragSource();
//        ds.createDefaultDragGestureRecognizer(tartanSecondChoice,
//                DnDConstants.ACTION_COPY, new DragGestureListImp());
//
//        DragSource ds2 = new DragSource();
//        ds2.createDefaultDragGestureRecognizer(tartanFirstChoice,
//                DnDConstants.ACTION_COPY, new DragGestureListImp());


        new MyDropTargetListImp(tartanFirstChoice);
        new MyDropTargetListImp(tartanSecondChoice);
    }

    public void init() {
        //height then width

        Tartan t3 = new Tartan(small, 1);
        t3.addThread(Color.pink, 10, "ASDA", "a");
        t3.addThread(Color.white, 4, "BASDA", "b");

        Tartan t = new Tartan(medium, 1);
        t.addThread(Color.white, 10, "ASDA", "a");
        t.addThread(Color.black, 1, "BASDA", "b");

        Tartan t4 = new Tartan(medium, 1);
        t4.addThread(Color.yellow, 10, "ASDA", "a");
        t4.addThread(Color.orange, 4, "BASDA", "b");

        Tartan t2 = new Tartan(large, 1);
        t2.addThread(Color.blue, 10, "ASDA", "a");
        t2.addThread(Color.green, 4, "BASDA", "b");

        allTartans = new JPanel();
        tartanSingle = new TartanSingle(0, t3);
        tartanSingle1 = new TartanSingle(1, t3);
        tartanSingle2 = new TartanSingle(2, t3);
        //tartanResult = new TartanSingle(3);
        //tartanFirstChoice = new TartanSingle(4,t);
        //tartanSecondChoice = new TartanSingle(5,t4);
        tartanFirstChoice = createLayer(18, t);
        tartanSecondChoice = createLayer(18, t4);



        ImageIcon plus = new ImageIcon(this.getClass().getResource("resources/images/plus.png"));
        lblPlus = new JLabel(plus);

        allTartans.setBackground(Color.darkGray);
        allTartans.setPreferredSize(new Dimension(900, 250));
    }

    public TartanCombine() {
        init();

        //Tartan tartan = new Tartan

        this.setLayout(new MigLayout(""));
        this.setBackground(Color.cyan);
        this.add(lblNew, "Wrap,span");
        this.add(btnRefreshTartanList, "Wrap");

        this.add(tartanFirstChoice, mediumString);


        //tartanFirstChoice.setBounds(0, 0, 400, 400);
        this.add(lblPlus, "width 200, height 200");
        this.add(tartanSecondChoice, mediumString + ",wrap");

        //this.add(tartanResult, "wrap, width 320, height 320");

        this.add(allTartans, "span 3");


        allTartans.add(tartanSingle, smallString);
        allTartans.add(tartanSingle1, smallString);
        allTartans.add(tartanSingle2, smallString);


        setUpDragNDrop();

    }


    public void populateAllTartans(Tartan[] tartansArray) {
        for (int i = 0; i < tartansArray.length; i++) {
            //this.addNewTartan(tartansArray[i]);


        }
    }

    private void addNewTartan(Tartan tartan) {

    }

    public void addRefreshTartansList(ActionListener refreshTartansList) {
        btnRefreshTartanList.addActionListener(refreshTartansList);
    }

    public void updateTabChangedStatus(boolean tabChangedStatus) {
        tartanSingle.setUpdateStatus(tabChangedStatus);
        tartanSingle1.setUpdateStatus(tabChangedStatus);
        tartanSingle2.setUpdateStatus(tabChangedStatus);
        //tartanFirstChoice.setUpdateStatus(tabChangedStatus);
        //tartanSecondChoice.setUpdateStatus(tabChangedStatus);
        //tartanResult.setUpdateStatus(tabChangedStatus);
        System.out.println("released");
        //tartanFirstChoice.repaint();

        // STORE ALL TARTANS WITHIN ARRAY THEN LOOP ON EACH TARTAN AND UPDATE ITS CHANGE STATUS FOR BUG FIX
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

        DragGestureListImp()
        {

        }
        @Override
        public void dragGestureRecognized(DragGestureEvent event) {

            System.out.println("triggered outside");
            Cursor cursor = null;
            JLayer myTartanX = (JLayer) event.getComponent();
            TartanSingle myTartan = (TartanSingle) myTartanX.getView();

            System.out.println("About to print");
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
        DragGestureListImp1(JLayer aa )
        {
            currentLayer = aa;
        }
        @Override
        public void dragGestureRecognized(DragGestureEvent event) {

            System.out.println("triggered inside canvas method");
            Cursor cursor = null;
            JLayer myTartanX = currentLayer;
            TartanSingle myTartan = (TartanSingle) myTartanX.getView();

            System.out.println("About to print canvas");
            //System.out.println(myTartan.getTartan().toString());

            if (event.getDragAction() == DnDConstants.ACTION_COPY) {
                cursor = DragSource.DefaultCopyDrop;
            }
            Tartan tartan = myTartan.getTartan();

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

                System.out.println("dropped Event");
                Transferable tr = event.getTransferable();
                Tartan an = (Tartan) tr.getTransferData(dataFlavor);
                System.out.println("aa" + an);
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

    private JLayer<JComponent> createLayer(int index, Tartan tartanInserted) {
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

                }

            }

        };
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

        // create the layer for the panel using our custom layerUI
        return tempLayerUI;
    }

}
