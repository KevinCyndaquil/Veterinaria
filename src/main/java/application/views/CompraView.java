package application.views;


import application.views.components.*;
import application.views.components.Button;
import application.views.components.abstracts.CustomJFrame;
import application.views.utils.Fonts;
import application.views.utils.Positions;
import application.views.utils.ResponsiveDimension;
import mdlaf.MaterialLookAndFeel;
import mdlaf.components.combobox.MaterialComboBoxUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompraView extends CustomJFrame {
    private JPanel mainPanel;

    public CompraView() {
        super("Compra");
    }

    @Override
    public void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setSize(ResponsiveDimension.getNextResolution().getSize());
        mainPanel.setLocation(0, 0);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.white);
        add(mainPanel);

        BarLeft barLeft = new BarLeft();
        barLeft.setLocation(10, 10);
        //barLeft.setOpaque(false);
        mainPanel.add(barLeft);

        SectionRound section2 = new SectionRound();
        section2.setLocation(500, 50);
        //barLeft.setOpaque(false);
        mainPanel.add(section2);

        SectionRound section2_1 = new SectionRound();
        section2_1.setLocation(1021, 28);
        //barLeft.setOpaque(false);
        section2.add(section2_1);



        TextDisplay tTypesInvoice = new TextDisplay("Types Invoice");
        tTypesInvoice.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tTypesInvoice.setSize(tTypesInvoice.getPreferredSize());
        section2_1.add(tTypesInvoice);

        JComboBox<String> jCTypesInvoice = new JComboBox<>();
        jCTypesInvoice.addItem("Ticket");
        jCTypesInvoice.addItem("Factura");
        jCTypesInvoice.setSize(270, 40);
        //jCTypesInvoice.setUI(new MaterialComboBoxUI());
        section2_1.add(jCTypesInvoice);


        TextDisplay tdate = new TextDisplay("Date");
        tdate.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tdate.setSize(tdate.getPreferredSize());
        section2.add(tdate);

        InputText date= new InputText("");

        date.setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 20f));
        date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        date.setHorizontalAlignment(JTextField.CENTER);
        date.setSize(190, 60);
        date.setFocusable(false);
        section2.add(date);

        TextDisplay tbarcode = new TextDisplay("Barcode");
        tbarcode.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tbarcode.setSize(tbarcode.getPreferredSize());
        section2.add(tbarcode);
        InputText barcode = new InputText("----");
        barcode.setSize(190, 60);
        section2.add(barcode);

        TextDisplay tname = new TextDisplay("Name");
        tname.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tname.setSize(tname.getPreferredSize());
        section2.add(tname);
        InputText name = new InputText("----");
        name.setSize(190, 60);
        name.setFocusable(false);
        section2.add(name);

        TextDisplay tamount = new TextDisplay("Amount");
        tamount.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tamount.setSize(tamount.getPreferredSize());
        section2.add(tamount);
        InputText amount = new InputText("----");
        amount.setSize(95, 60);
        section2.add(amount);

        TextDisplay tpresentation = new TextDisplay("Presentation");
        tpresentation.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tpresentation.setSize(tpresentation.getPreferredSize());
        section2.add(tpresentation);
        InputText presentation = new InputText("----");
        presentation.setSize(190, 60);
        presentation.setFocusable(false);
        section2.add(presentation);

        TextDisplay tprice = new TextDisplay("Price");
        tprice.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tprice.setSize(tprice.getPreferredSize());
        section2.add(tprice);
        InputText precio = new InputText("$$$$");
        precio.setSize(190, 60);
        precio.setFocusable(false);
        section2.add(precio);

        TextDisplay tdescription = new TextDisplay("Description");
        tdescription.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tdescription.setSize(tdescription.getPreferredSize());
        section2.add(tdescription);
        InputText description = new InputText("****");
        description.setSize(451, 60);
        description.setFocusable(false);
        section2.add(description);

        Button btnNew = new Button("New");
        btnNew.setSize(130, 49);
        section2.add(btnNew);

        Button btnAdd = new Button("Add");
        btnAdd.setSize(130, 49);
        section2.add(btnAdd);

        TextDisplay tsubtotal = new TextDisplay("Subtotal: $");
        tsubtotal.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tsubtotal.setSize(270, 91);
        tsubtotal.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
        tsubtotal.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(tsubtotal);

        TextDisplay tiva = new TextDisplay("IVA 16%: $");
        tiva.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 24f));
        tiva.setSize(270, 91);
        tiva.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        tiva.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(tiva);

        TextDisplay ttotal = new TextDisplay("Total: $");
        ttotal.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 35f));
        ttotal.setSize(270, 91);
        ttotal.setHorizontalAlignment(SwingConstants.LEFT);
        mainPanel.add(ttotal);

        Button btnPay = new Button("Pay");
        btnPay.setSize(270, 49);
        mainPanel.add(btnPay);

        SectionRound section3 = new SectionRound();
        section3.setLocation(500, 380);
        //barLeft.setOpaque(false);
        mainPanel.add(section3);

        JTable table = new JTable();
        table.setSize(970, 643);
        table.setRowHeight(40);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Num");
        model.addColumn("Barcode");
        model.addColumn("Name");
        model.addColumn("Amount");
        model.addColumn("Price");

        model.addRow(new Object[]{"1", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"2", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"3", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"4", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"5", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"6", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"7", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"8", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"9", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"10", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"11", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"12", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"13", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"14", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"15", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"16", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"17", "123456789", "Coca Cola", "2", "$15.00"});
        model.addRow(new Object[]{"18", "123456789", "Coca Cola", "2", "$15.00"});

        table.setModel(model);

        table.setBackground(Color.white);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 10, 970, 643);
        scrollPane.setViewportView(table);
        section3.add(scrollPane);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //System.out.println("content\t" + getContentPane().getSize());
                //System.out.println("frame\t" + getSize());
                //if size window is bigger than HD then resize mainPanel to window size
                mainPanel.setSize(getContentPane().getSize());
                mainPanel.setBackground(Color.white);


                section2.setSize(1391, 305);
                section2.setBackground(Color.white);
                section2_1.setSize(340, 120);
                section2_1.setBackground(Color.white);
                section3.setSize(1002, 663);
                section3.setBackground(Color.white);

                tdate.setLocation(section2, Positions.LEFT, 30, -115);
                date.setLocation(section2, Positions.LEFT, 30, -70);

                tbarcode.setLocation(section2, Positions.LEFT, 290, -115);
                barcode.setLocation(section2, Positions.LEFT, 290, -70);

                tname.setLocation(section2, Positions.LEFT, 550, -115);
                name.setLocation(section2, Positions.LEFT, 550, -70);

                tamount.setLocation(section2, Positions.LEFT, 810, -115);
                amount.setLocation(section2, Positions.LEFT, 810, -70);

                tpresentation.setLocation(section2, Positions.LEFT, 550, 25);
                presentation.setLocation(section2, Positions.LEFT, 550, 70);

                tprice.setLocation(section2, Positions.LEFT, 810, 25);
                precio.setLocation(section2, Positions.LEFT, 810, 70);

                tdescription.setLocation(section2, Positions.LEFT, 30, 25);
                description.setLocation(section2, Positions.LEFT, 30, 70);

                btnAdd.setLocation(section2, Positions.RIGHT, -210, 70);
                btnNew.setLocation(section2, Positions.RIGHT, -60, 70);

                tsubtotal.setLocation(mainPanel, Positions.RIGHT, -85, -100);
                tiva.setLocation(mainPanel, Positions.RIGHT, -85, -10);
                ttotal.setLocation(mainPanel, Positions.RIGHT, -85, 80);

                btnPay.setLocation(mainPanel, Positions.RIGHT, -80, 180);

                jCTypesInvoice.setLocation(35, 50);
                tTypesInvoice.setLocation(section2_1, Positions.CENTER, 0, -30);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing

            try {
                UIManager.setLookAndFeel(new MaterialLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            new CompraView().setVisible(true);

        });
    }
}
