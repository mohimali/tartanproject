package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import tartan.Tartan;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
    TartanSingle tartanFirstChoice;
    TartanSingle tartanSecondChoice;

    BorderHoverJPanel tartanFirstChoiceContainer;

    int large = 300;
    int largeOfset = large+20;

    int medium = 150;
    int mediumOfset = medium + 50;

    int small = 100;
    int smallOfset = small;

    String mediumString = "width " + mediumOfset + ",height " + mediumOfset + "push, align center";
    String smallString = "width " + smallOfset + ",height " + smallOfset + ",gap 20";

    DataFlavor dataFlavor = new DataFlavor(Tartan.class,
            Tartan.class.getSimpleName());

    private void setUpDragNDrop() {
        DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(tartanSecondChoice,
                DnDConstants.ACTION_COPY, new DragGestureListImp());

        DragSource ds2 = new DragSource();
        ds2.createDefaultDragGestureRecognizer(tartanFirstChoice,
                DnDConstants.ACTION_COPY, new DragGestureListImp());



        new MyDropTargetListImp(tartanFirstChoice);
        new MyDropTargetListImp(tartanSecondChoice);
    }

    public void init() {
        //height then width

        allTartans = new JPanel(new MigLayout(""));
        tartanSingle = new TartanSingle(0);
        tartanSingle1 = new TartanSingle(1);
        tartanSingle2 = new TartanSingle(2);
        tartanResult = new TartanSingle(3);
        tartanFirstChoice = new TartanSingle(4);
        tartanSecondChoice = new TartanSingle(5);
        btnRefreshTartanList = new JButton("Load Tartans");

        tartanFirstChoiceContainer = new BorderHoverJPanel();
        tartanFirstChoice.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tartanFirstChoiceContainer.dispatchEvent(e);
                updateTabChangedStatus(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tartanFirstChoiceContainer.dispatchEvent(e);
                updateTabChangedStatus(true);
            }
        });

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

        Tartan t = new Tartan(medium, 1);

        t.addThread(Color.white, 10, "ASDA", "a");
        t.addThread(Color.black, 1, "BASDA", "b");

        Tartan t2 = new Tartan(large, 1);

        t2.addThread(Color.blue, 10, "ASDA", "a");
        t2.addThread(Color.green, 4, "BASDA", "b");


        Tartan t4 = new Tartan(medium, 1);

        t4.addThread(Color.yellow, 10, "ASDA", "a");
        t4.addThread(Color.orange, 4, "BASDA", "b");

        tartanFirstChoice.addTartanToDisplay(t);
        tartanFirstChoice.repaint();
        tartanSecondChoice.addTartanToDisplay(t4);
        tartanSecondChoice.repaint();

        tartanFirstChoiceContainer.add(tartanFirstChoice,"push, align center");


        this.add(tartanFirstChoiceContainer,mediumString);



        //tartanFirstChoice.setBounds(0, 0, 400, 400);
        this.add(lblPlus, "width 200, height 200");
        this.add(tartanSecondChoice, " wrap");



        tartanResult.addTartanToDisplay(t2);
        //this.add(tartanResult, "wrap, width 320, height 320");

        this.add(allTartans, "span 3");

        Tartan t3 = new Tartan(small, 1);

        t3.addThread(Color.pink, 10, "ASDA", "a");
        t3.addThread(Color.white, 4, "BASDA", "b");


        tartanSingle.addTartanToDisplay(t3);
        tartanSingle1.addTartanToDisplay(t3);
        tartanSingle2.addTartanToDisplay(t3);

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
        tartanFirstChoice.setUpdateStatus(tabChangedStatus);
        tartanSecondChoice.setUpdateStatus(tabChangedStatus);
        tartanResult.setUpdateStatus(tabChangedStatus);

        // STORE ALL TARTANS WITHIN ARRAY THEN LOOP ON EACH TARTAN AND UPDATE ITS CHANGE STATUS FOR BUG FIX
    }

    class TransferableTartan implements Transferable {

        private Tartan tartan;

        public TransferableTartan(Tartan ani) {

            this.tartan = ani;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { dataFlavor };
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

        @Override
        public void dragGestureRecognized(DragGestureEvent event) {

            System.out.println("triggered outside");
            Cursor cursor = null;
            TartanSingle myTartan = (TartanSingle) event.getComponent();

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
        private TartanSingle panel;

        public MyDropTargetListImp(TartanSingle panel) {
            this.panel = panel;

            dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this,
                    true, null);
        }


        public void drop(DropTargetDropEvent event) {
            try {

                System.out.println("dropped Event");
                Transferable tr = event.getTransferable();
                Tartan an = (Tartan) tr.getTransferData(dataFlavor);

                if (event.isDataFlavorSupported(dataFlavor)) {
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    this.panel.addTartanToDisplay(an);

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

}
