package application.views.panels;

import application.views.components.*;
import application.views.components.Button;
import application.views.components.abstracts.CustomJPanel;
import application.views.utils.Fonts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AltaCompra extends CustomJPanel{
    @Override
    public void initComponents() {
        setLocation(0, 0);
        setBackground(Color.white);

        section2 = new SectionRound();
        section2.setLocation(10, 10);
        add(section2);

        section3 = new SectionRound();
        add(section3);

        table = new JTable(new TableDetalleTicketModel());
        table.setSize(970, 643);
        table.setRowHeight(40);
        table.setBackground(Color.WHITE);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 10, 970, 643);
        scrollPane.setViewportView(table);
        scrollPane.setBackground(table.getBackground());

        section3.add(scrollPane);

        tTypesInvoice = new TextDisplay("Types Invoice");
        tTypesInvoice.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tTypesInvoice.setSize(tTypesInvoice.getPreferredSize());

        jCTypesInvoice = new JComboBox<>();
        jCTypesInvoice.addItem("Ticket");
        jCTypesInvoice.addItem("Factura");
        jCTypesInvoice.setSize(270, 40);

        tDate = new TextDisplay("Date");
        tDate.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tDate.setSize(tDate.getPreferredSize());
        tDate.setFocusable(false);
        section2.add(tDate);

        iDate = new InputText("dd/mm/yy");
        iDate.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        iDate.setSize(150, 30);
        section2.add(iDate);

        tBarCode = new TextDisplay("Barcode");
        tBarCode.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tBarCode.setSize(tBarCode.getPreferredSize());
        section2.add(tBarCode);

        iBarCode = new InputText("----");
        iBarCode.setSize(150, 30);
        section2.add(iBarCode);

        tName = new TextDisplay("Name");
        tName.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tName.setSize(tName.getPreferredSize());
        section2.add(tName);

        iName = new InputText("----");
        iName.setSize(150, 30);
        iName.setFocusable(false);
        section2.add(iName);

        tAmount = new TextDisplay("Amount");
        tAmount.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tAmount.setSize(tAmount.getPreferredSize());
        section2.add(tAmount);

        iAmount = new InputText("----");
        iAmount.setSize(95, 30);
        section2.add(iAmount);

        tPresentation = new TextDisplay("Presentation");
        tPresentation.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tPresentation.setSize(tPresentation.getPreferredSize());
        section2.add(tPresentation);

        iPresentation = new InputText("----");
        iPresentation.setSize(150, 30);
        iPresentation.setFocusable(false);
        section2.add(iPresentation);

        tPrice = new TextDisplay("Price");
        tPrice.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tPrice.setSize(tPrice.getPreferredSize());
        section2.add(tPrice);

        iPrecio = new InputText("$$$$");
        iPrecio.setSize(150, 30);
        iPrecio.setFocusable(false);
        section2.add(iPrecio);

        tDescription = new TextDisplay("Description");
        tDescription.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tDescription.setSize(tDescription.getPreferredSize());
        section2.add(tDescription);

        iDescription = new InputText("****");
        iDescription.setSize(451, 30);
        iDescription.setFocusable(false);
        section2.add(iDescription);

        tSubtotal = new TextDisplay("Subtotal");
        tSubtotal.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tSubtotal.setSize(200, 40);
        tSubtotal.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        tSubtotal.setHorizontalAlignment(SwingConstants.LEFT);
        section2.add(tSubtotal);

        iSubtotal = new InputText("$");
        iSubtotal.setFocusable(false);
        section2.add(iSubtotal);

        tIva = new TextDisplay("IVA 16%");
        tIva.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tIva.setSize(200, 40);
        tIva.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        tIva.setHorizontalAlignment(SwingConstants.LEFT);
        section2.add(tIva);

        iIva = new InputText("$");
        iIva.setFocusable(false);
        section2.add(iIva);

        tTotal = new TextDisplay("Total: $");
        tTotal.setFont(Fonts.load(Fonts.ROBOTO).deriveFont(Font.PLAIN, 16f));
        tTotal.setSize(200, 40);
        tTotal.setHorizontalAlignment(SwingConstants.LEFT);
        tTotal.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        section2.add(tTotal);

        iTotal = new InputText("$$$");
        iTotal.setFocusable(false);
        section2.add(iTotal);

        btnNew = new Button("New");
        btnNew.setSize(130, 49);
        section2.add(btnNew);

        btnAdd = new Button("Add");
        btnAdd.setSize(130, 49);
        section2.add(btnAdd);

        btnPay = new Button("Pay");
        btnPay.setSize(130, 49);
        section2.add(btnPay);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                section2.setSize(getWidth() - 20, 300);
                section2.setBackground(Color.white);

                section3.setLocation(
                        section2.getX(),
                        section2.getY() + section2.getHeight() + 20);
                section3.setSize(
                        getWidth() - 20,
                        getHeight() - section3.getY() - 30);
                section3.setBackground(Color.white);

                Point fSection = new Point(
                        section2.getWidth() / 4,
                        30);

                Point sSection = new Point(
                        (section2.getWidth() - 250) / 3,
                        fSection.y + 80);

                tDate.setLocation(10, fSection.y);
                tBarCode.setLocation(fSection.x + 10, fSection.y);
                tName.setLocation(fSection.x * 2 + 30, fSection.y);
                tAmount.setLocation(fSection.x * 3 + 50, fSection.y);

                iDate.setBounds(
                        tDate.getX(),
                        tDate.getY() + tDate.getHeight() + 10,
                        fSection.x - 20,
                        fSection.y);
                iBarCode.setBounds(
                        tBarCode.getX(),
                        tBarCode.getY() + tBarCode.getHeight() + 10,
                        fSection.x - 20,
                        fSection.y);
                iName.setBounds(
                        tName.getX(),
                        tName.getY() + tName.getHeight() + 10,
                        fSection.x - 20,
                        fSection.y);
                iAmount.setBounds(
                        tAmount.getX(),
                        tAmount.getY() + tDate.getHeight() + 10,
                        fSection.x - 80,
                        fSection.y);

                tDescription.setLocation(10, sSection.y);
                tPresentation.setLocation(sSection.x + 30, sSection.y);
                tPrice.setLocation(sSection.x * 2 + 50, sSection.y);

                iDescription.setBounds(
                        tDescription.getX(),
                        tDescription.getY() + tDescription.getHeight() + 10,
                        sSection.x - 20,
                        fSection.y);
                iPresentation.setBounds(
                        tPresentation.getX(),
                        tPresentation.getY() + tPresentation.getHeight() + 10,
                        sSection.x - 20,
                        fSection.y);
                iPrecio.setBounds(
                        tPrice.getX(),
                        tPrice.getY()  + tPrice.getHeight() + 10,
                        sSection.y - 20,
                        fSection.y);

                tIva.setLocation(
                        section2.getWidth() - tIva.getWidth() - 80,
                        iPrecio.getY() - 30);
                tTotal.setLocation(
                        iDescription.getX(),
                        section2.getHeight() - tTotal.getHeight() - 80);
                tSubtotal.setLocation(
                        tTotal.getX() + tTotal.getWidth() + 30,
                        tTotal.getY());

                iIva.setBounds(
                        tIva.getX(),
                        tIva.getY() + tIva.getHeight() + 10,
                        tIva.getWidth(),
                        30);

                iTotal.setBounds(
                        tTotal.getX(),
                        tTotal.getY() + tTotal.getHeight() + 10,
                        tTotal.getWidth(),
                        30);

                iSubtotal.setBounds(
                        tSubtotal.getX(),
                        tSubtotal.getY() + tSubtotal.getHeight() + 10,
                        tSubtotal.getWidth(),
                        30);

                btnAdd.setLocation(iPrecio.getX() - 20, iPrecio.getY() + iPrecio.getHeight() + 60);
                btnNew.setLocation(btnAdd.getX() + btnAdd.getWidth() + 20, btnAdd.getY());
                btnPay.setLocation(btnNew.getX() + btnNew.getWidth() + 20, btnNew.getY());

                table.setBounds(10, 10, section3.getWidth() - 20, section3.getHeight() - 30);
                scrollPane.setBounds(table.getBounds());
            }
        });
    }

    public SectionRound section2;
    public SectionRound section2_1;
    public SectionRound section3;

    public JTable table;

    public JScrollPane scrollPane;

    public TextDisplay tTypesInvoice;
    public TextDisplay tDate;
    public TextDisplay tBarCode;
    public TextDisplay tName;
    public TextDisplay tAmount;
    public TextDisplay tPresentation;
    public TextDisplay tPrice;
    public TextDisplay tDescription;
    public TextDisplay tSubtotal;
    public TextDisplay tIva;
    public TextDisplay tTotal;

    public JComboBox<String> jCTypesInvoice;
    public InputText iDate;
    public InputText iBarCode;
    public InputText iName;
    public InputText iAmount;
    public InputText iPresentation;
    public InputText iPrecio;
    public InputText iDescription;
    public InputText iIva;
    public InputText iTotal;
    public InputText iSubtotal;

    public Button btnNew;
    public Button btnAdd;
    public Button btnPay;
}
