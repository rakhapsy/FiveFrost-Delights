/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import koneksi.KoneksiDB;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utils.SessionManager;

/**
 *
 * @author rakha
 */
public class Dashboard extends javax.swing.JFrame {
    
    private CardLayout cardLayout;
    
    // Sidebar buttons — Main Menu
    private JButton btnDashboard;

    // Sub menu: Data Master
    private JButton btnDataMaster;
    private JPanel subDataMaster;
    private JButton btnProduk, btnPelanggan, btnSupplier;

    // Sub menu: Transaksi
    private JButton btnTransaksi;
    private JPanel subTransaksi;
    private JButton btnMasuk, btnKeluar, btnRetur;

    // Sub menu: Laporan
    private JButton btnLaporan;
    private JPanel subLaporan;
    private JButton btnLapStok, btnLapMasuk, btnLapKeluar, btnLapRetur;
    
    // Sidebar buttons — Main Menu
    private JButton btnPengguna;
    
    private JLabel lblIconUser;
    private JLabel lblUserName;
    private JLabel lblSubtitle;
    
    private DefaultTableModel model;
    
    private javax.swing.Timer timerDashboard;

    /**
     * Creates new form AIOView
     */
    public Dashboard() {
        initComponents();
//        if (!SessionManager.getInstance().isLoggedIn()) {
//            this.dispose();
//            return;
//        }
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent e) {
//                KoneksiDB.closeKoneksi();
//                System.exit(0);
//            }
//        });
        buildSidebar();
        setActiveButton(btnDashboard);
        stokAlertPanel.setVisible(false);
        showPanel("Dashboard");
        loadSessionData();
        loadDashboardData();
        tampilGrafikTerlaris();
        tampilGrafikBatang();
        startAutoRefresh();
        PanelProduk pnlProduk = new PanelProduk();
        PanelPelanggan pnlPelanggan = new PanelPelanggan();
        PanelSupplier pnlSupplier = new PanelSupplier();
        PanelTransaksiMasuk pnlTrxMasuk = new PanelTransaksiMasuk();
        PanelTransaksiKeluar pnlTrxKeluar = new PanelTransaksiKeluar();
        PanelTransaksiRetur pnlTrxRetur = new PanelTransaksiRetur();
        PanelLaporanStok pnlLprStok = new PanelLaporanStok();
        PanelLaporanMasuk pnlLprMasuk = new PanelLaporanMasuk();
        PanelLaporanKeluar pnlLprKeluar = new PanelLaporanKeluar();
        PanelLaporanRetur pnlLprRetur = new PanelLaporanRetur();
        PanelPengguna pnlPengguna = new PanelPengguna();
        mainPanel.add(pnlProduk, "Produk");
        mainPanel.add(pnlPelanggan, "Pelanggan");
        mainPanel.add(pnlSupplier, "Supplier");
        mainPanel.add(pnlTrxMasuk, "Transaksi Masuk");
        mainPanel.add(pnlTrxKeluar, "Transaksi Keluar");
        mainPanel.add(pnlTrxRetur, "Transaksi Retur");
        mainPanel.add(pnlLprStok, "Laporan Stok");
        mainPanel.add(pnlLprMasuk, "Laporan Masuk");
        mainPanel.add(pnlLprKeluar, "Laporan Keluar");
        mainPanel.add(pnlLprRetur, "Laporan Retur");
        mainPanel.add(pnlPengguna, "Pengguna");
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    private void buildSidebar() {
        cardLayout = (CardLayout) mainPanel.getLayout();

        cardLayout.addLayoutComponent(menuUtamaPanel, "Dashboard");
//        cardLayout.addLayoutComponent(menuProdukPanel, "Data Produk");
//        cardLayout.addLayoutComponent(menuPelangganPanel, "Data Pelanggan");
//        cardLayout.addLayoutComponent(menuSupplierPanel, "Data Supplier");

        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        // sidebarPanel.setBackground(new Color(44, 62, 80));

        JPanel logoPanel = new JPanel(new BorderLayout());
        // logoPanel.setBackground(new Color(31, 45, 58));
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        logoPanel.setPreferredSize(new Dimension(220, 65));
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblUserName = new JLabel("Username");
        lblUserName.setForeground(new Color(255, 255, 255));
        lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblUserName.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));

        lblSubtitle = new JLabel("Role");
        lblSubtitle.setForeground(new Color(255, 255, 255));
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));

        JPanel logoText = new JPanel();
        logoText.setLayout(new BoxLayout(logoText, BoxLayout.Y_AXIS));
        logoText.setBackground(new Color(44, 62, 80));
        logoText.add(Box.createVerticalGlue());
        logoText.add(lblUserName);
        logoText.add(lblSubtitle);
        logoText.add(Box.createVerticalGlue());

        logoPanel.add(logoText, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(52, 73, 94));
        separator.setBackground(new Color(52, 73, 94));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebarPanel.add(logoPanel);
        sidebarPanel.add(separator);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        // ------------------------------------------
        // MENU UTAMA
        // ------------------------------------------
        // sidebarPanel.add(createMenuLabel("Main"));

        // --- Dashboard ---
        btnDashboard = createMenuButton("Dashboard");
        btnDashboard.addActionListener(e -> {
            setActiveButton(btnDashboard);
            showPanel("Dashboard");
        });
        sidebarPanel.add(btnDashboard);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 4)));

        // ------------------------------------------
        // DATA MASTER
        // ------------------------------------------
        // sidebarPanel.add(createMenuLabel("Data Master"));

        btnDataMaster = createMenuButton("Data Master");

        btnProduk    = createSubMenuButton("Produk");
        btnPelanggan = createSubMenuButton("Pelanggan");
        btnSupplier  = createSubMenuButton("Supplier");
        subDataMaster = createSubMenuPanel(btnProduk, btnPelanggan, btnSupplier);

        btnDataMaster.addActionListener(e -> {
            boolean visible = subDataMaster.isVisible();
            subDataMaster.setVisible(!visible);
            btnDataMaster.setText(!visible ? "Data Master ▼" : "Data Master");
            sidebarPanel.revalidate();
            sidebarPanel.repaint();
        });

        btnProduk.addActionListener(e -> {
            setActiveButton(btnProduk);
            showPanel("Produk");
        });
        btnPelanggan.addActionListener(e -> {
            setActiveButton(btnPelanggan);
            showPanel("Pelanggan");
        });
        btnSupplier.addActionListener(e -> {
            setActiveButton(btnSupplier);
            showPanel("Supplier");
        });

        sidebarPanel.add(btnDataMaster);
        sidebarPanel.add(subDataMaster);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 4)));

        // ------------------------------------------
        // TRANSAKSI
        // ------------------------------------------
        // sidebarPanel.add(createMenuLabel("Transaksi"));

        btnTransaksi = createMenuButton("Transaksi");

        btnMasuk  = createSubMenuButton("Masuk");
        btnKeluar = createSubMenuButton("Keluar");
        btnRetur  = createSubMenuButton("Retur");
        subTransaksi = createSubMenuPanel(btnMasuk, btnKeluar, btnRetur);

        btnTransaksi.addActionListener(e -> {
            boolean visible = subTransaksi.isVisible();
            subTransaksi.setVisible(!visible);
            btnTransaksi.setText(!visible ? "Transaksi ▼" : "Transaksi");
            sidebarPanel.revalidate();
            sidebarPanel.repaint();
        });

        btnMasuk.addActionListener(e -> {
            setActiveButton(btnMasuk);
            showPanel("Transaksi Masuk");
        });
        btnKeluar.addActionListener(e -> {
            setActiveButton(btnKeluar);
            showPanel("Transaksi Keluar");
        });
        btnRetur.addActionListener(e -> {
            setActiveButton(btnRetur);
            showPanel("Transaksi Retur");
        });

        sidebarPanel.add(btnTransaksi);
        sidebarPanel.add(subTransaksi);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 4)));

        // ------------------------------------------
        // LAPORAN
        // ------------------------------------------
        // sidebarPanel.add(createMenuLabel("Laporan"));

        btnLaporan = createMenuButton("Laporan");

        btnLapStok    = createSubMenuButton("Stok");
        btnLapMasuk   = createSubMenuButton("Transaksi Masuk");
        btnLapKeluar  = createSubMenuButton("Transaksi Keluar");
        btnLapRetur   = createSubMenuButton("Retur");
        subLaporan = createSubMenuPanel(btnLapStok, btnLapMasuk, btnLapKeluar, btnLapRetur);

        btnLaporan.addActionListener(e -> {
            boolean visible = subLaporan.isVisible();
            subLaporan.setVisible(!visible);
            btnLaporan.setText(!visible ? "Laporan ▼" : "Laporan");
            sidebarPanel.revalidate();
            sidebarPanel.repaint();
        });

        btnLapStok.addActionListener(e   -> { setActiveButton(btnLapStok);   showPanel("Laporan Stok");   });
        btnLapMasuk.addActionListener(e  -> { setActiveButton(btnLapMasuk);  showPanel("Laporan Masuk");  });
        btnLapKeluar.addActionListener(e -> { setActiveButton(btnLapKeluar); showPanel("Laporan Keluar"); });
        btnLapRetur.addActionListener(e  -> { setActiveButton(btnLapRetur);  showPanel("Laporan Retur");  });

        sidebarPanel.add(btnLaporan);
        sidebarPanel.add(subLaporan);
        
        // ------------------------------------------
        // PENGGUNA
        // ------------------------------------------
        // sidebarPanel.add(createMenuLabel("Pengguna"));

        // --- Pengguna ---
        btnPengguna = createMenuButton("Pengguna");
        btnPengguna.addActionListener(e -> {
            setActiveButton(btnPengguna);
            showPanel("Pengguna");
        });
        sidebarPanel.add(btnPengguna);

        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        
        try {
            ImageIcon iconDashboard = new ImageIcon(getClass().getResource("/img/icons/icon-dashboard.png"));
            ImageIcon iconDataMaster = new ImageIcon(getClass().getResource("/img/icons/icon-data-master.png"));
            ImageIcon iconProduk = new ImageIcon(getClass().getResource("/img/icons/icon-produk.png"));
            ImageIcon iconPelanggan = new ImageIcon(getClass().getResource("/img/icons/icon-pelanggan.png"));
            ImageIcon iconSupplier = new ImageIcon(getClass().getResource("/img/icons/icon-supplier.png"));
            ImageIcon iconTransaksi = new ImageIcon(getClass().getResource("/img/icons/icon-transaksi.png"));
            ImageIcon iconTrxMasuk = new ImageIcon(getClass().getResource("/img/icons/icon-transaksi-masuk.png"));
            ImageIcon iconTrxKeluar = new ImageIcon(getClass().getResource("/img/icons/icon-transaksi-keluar.png"));
            ImageIcon iconTrxRetur = new ImageIcon(getClass().getResource("/img/icons/icon-transaksi-retur.png"));
            ImageIcon iconLaporan = new ImageIcon(getClass().getResource("/img/icons/icon-laporan.png"));
            ImageIcon iconLaporanStok = new ImageIcon(getClass().getResource("/img/icons/icon-laporan-stok.png"));
            ImageIcon iconLaporanMasukKeluar = new ImageIcon(getClass().getResource("/img/icons/icon-laporan-masuk-keluar.png"));
            ImageIcon iconLaporanRetur = new ImageIcon(getClass().getResource("/img/icons/icon-laporan-retur.png"));
            ImageIcon iconPengguna = new ImageIcon(getClass().getResource("/img/icons/icon-pengguna.png"));
            btnDashboard.setIcon(iconDashboard);
            btnDataMaster.setIcon(iconDataMaster);
            btnProduk.setIcon(iconProduk);
            btnPelanggan.setIcon(iconPelanggan);
            btnSupplier.setIcon(iconSupplier);
            btnTransaksi.setIcon(iconTransaksi);
            btnMasuk.setIcon(iconTrxMasuk);
            btnKeluar.setIcon(iconTrxKeluar);
            btnRetur.setIcon(iconTrxRetur);
            btnLaporan.setIcon(iconLaporan);
            btnLapStok.setIcon(iconLaporanStok);
            btnLapMasuk.setIcon(iconLaporanMasukKeluar);
            btnLapKeluar.setIcon(iconLaporanMasukKeluar);
            btnLapRetur.setIcon(iconLaporanRetur);
            btnPengguna.setIcon(iconPengguna);
        } catch (Exception e) {
            System.out.println("Ikon sidebar tidak ditemukan!");
        }

        sidebarPanel.add(Box.createVerticalGlue());
    }
    
    public void aturHakAkses(String role) {
        lblSubtitle.setText(role);

        btnDataMaster.setVisible(true);
        btnPengguna.setVisible(true);
        btnLaporan.setVisible(true);

        if (role.equalsIgnoreCase("Admin")) {
            btnPengguna.setVisible(false);

        } else if (role.equalsIgnoreCase("Gudang")) {
            btnDataMaster.setVisible(false);
            subDataMaster.setVisible(false);
            btnPengguna.setVisible(false);
            btnLaporan.setVisible(false);
            subLaporan.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuDataMaster = new javax.swing.JPopupMenu();
        menuProduk = new javax.swing.JMenuItem();
        menuPelanggan = new javax.swing.JMenuItem();
        menuSupplier = new javax.swing.JMenuItem();
        headerPanel = new javax.swing.JPanel();
        headerTitle = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        sidebarPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        menuUtamaPanel = new javax.swing.JPanel();
        jumboMenuUtama = new javax.swing.JLabel();
        stokPanel = new javax.swing.JPanel();
        stokLabel = new javax.swing.JLabel();
        jmlStokLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        trxKeluarPanel = new javax.swing.JPanel();
        trxKeluarLabel = new javax.swing.JLabel();
        jmlTrxKeluarLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        trxKeluarBlnThnLabel = new javax.swing.JLabel();
        trxMasukPanel = new javax.swing.JPanel();
        trxMasukLabel = new javax.swing.JLabel();
        jmlTrxMasukLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        trxMasukBlnThnLabel = new javax.swing.JLabel();
        trxKeluarPanel1 = new javax.swing.JPanel();
        trxKeluarLabel1 = new javax.swing.JLabel();
        jmlTrxReturLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        trxReturBlnThnLabel = new javax.swing.JLabel();
        stokAlertPanel = new javax.swing.JPanel();
        lblAlertStok = new javax.swing.JLabel();
        panelGrafikTerlaris = new javax.swing.JPanel();
        stokPanel1 = new javax.swing.JPanel();
        stokLabel1 = new javax.swing.JLabel();
        jmlPelangganLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        trxMasukBlnThnLabel1 = new javax.swing.JLabel();
        panelGrafikBatang = new javax.swing.JPanel();

        menuProduk.setText("jMenuItem1");
        menuDataMaster.add(menuProduk);

        menuPelanggan.setText("jMenuItem1");
        menuDataMaster.add(menuPelanggan);

        menuSupplier.setText("jMenuItem1");
        menuDataMaster.add(menuSupplier);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FiveFrost Delights - Dashboard");

        headerPanel.setBackground(new java.awt.Color(0, 102, 102));
        headerPanel.setPreferredSize(new java.awt.Dimension(1080, 75));

        headerTitle.setFont(new java.awt.Font("Tahoma", 1, 34)); // NOI18N
        headerTitle.setForeground(new java.awt.Color(255, 255, 255));
        headerTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        headerTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon-white-nav.png"))); // NOI18N
        headerTitle.setText("FiveFrost Delights");

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(0, 102, 102));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon-logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(headerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1505, Short.MAX_VALUE)
                .addComponent(btnLogout))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(headerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

        sidebarPanel.setBackground(new java.awt.Color(0, 102, 102));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(220, 0));
        sidebarPanel.setLayout(new javax.swing.BoxLayout(sidebarPanel, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(sidebarPanel, java.awt.BorderLayout.LINE_START);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.CardLayout());

        menuUtamaPanel.setBackground(new java.awt.Color(255, 255, 255));

        jumboMenuUtama.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jumboMenuUtama.setForeground(new java.awt.Color(0, 102, 102));
        jumboMenuUtama.setText("Halo Username, Selamat Bekerja!");

        stokPanel.setBackground(new java.awt.Color(0, 153, 204));

        stokLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        stokLabel.setForeground(new java.awt.Color(255, 255, 255));
        stokLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon-produk.png"))); // NOI18N
        stokLabel.setText("Produk Aktif");

        jmlStokLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jmlStokLabel.setForeground(new java.awt.Color(255, 255, 255));
        jmlStokLabel.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produk");

        javax.swing.GroupLayout stokPanelLayout = new javax.swing.GroupLayout(stokPanel);
        stokPanel.setLayout(stokPanelLayout);
        stokPanelLayout.setHorizontalGroup(
            stokPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stokPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stokPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stokLabel)
                    .addGroup(stokPanelLayout.createSequentialGroup()
                        .addComponent(jmlStokLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        stokPanelLayout.setVerticalGroup(
            stokPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stokPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stokLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(stokPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlStokLabel)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        trxKeluarPanel.setBackground(new java.awt.Color(204, 0, 51));
        trxKeluarPanel.setPreferredSize(new java.awt.Dimension(313, 133));

        trxKeluarLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        trxKeluarLabel.setForeground(new java.awt.Color(255, 255, 255));
        trxKeluarLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon-transaksi-keluar.png"))); // NOI18N
        trxKeluarLabel.setText("Transaksi Keluar");

        jmlTrxKeluarLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jmlTrxKeluarLabel.setForeground(new java.awt.Color(255, 255, 255));
        jmlTrxKeluarLabel.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Transaksi");

        trxKeluarBlnThnLabel.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        trxKeluarBlnThnLabel.setForeground(new java.awt.Color(255, 255, 255));
        trxKeluarBlnThnLabel.setText("bulan tahun");

        javax.swing.GroupLayout trxKeluarPanelLayout = new javax.swing.GroupLayout(trxKeluarPanel);
        trxKeluarPanel.setLayout(trxKeluarPanelLayout);
        trxKeluarPanelLayout.setHorizontalGroup(
            trxKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trxKeluarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trxKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(trxKeluarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trxKeluarPanelLayout.createSequentialGroup()
                        .addComponent(jmlTrxKeluarLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addComponent(trxKeluarBlnThnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        trxKeluarPanelLayout.setVerticalGroup(
            trxKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trxKeluarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trxKeluarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(trxKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlTrxKeluarLabel)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(trxKeluarBlnThnLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        trxMasukPanel.setBackground(new java.awt.Color(0, 153, 153));
        trxMasukPanel.setPreferredSize(new java.awt.Dimension(313, 133));

        trxMasukLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        trxMasukLabel.setForeground(new java.awt.Color(255, 255, 255));
        trxMasukLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon-transaksi-masuk.png"))); // NOI18N
        trxMasukLabel.setText("Transaksi Masuk");

        jmlTrxMasukLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jmlTrxMasukLabel.setForeground(new java.awt.Color(255, 255, 255));
        jmlTrxMasukLabel.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Transaksi");

        trxMasukBlnThnLabel.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        trxMasukBlnThnLabel.setForeground(new java.awt.Color(255, 255, 255));
        trxMasukBlnThnLabel.setText("bulan tahun");

        javax.swing.GroupLayout trxMasukPanelLayout = new javax.swing.GroupLayout(trxMasukPanel);
        trxMasukPanel.setLayout(trxMasukPanelLayout);
        trxMasukPanelLayout.setHorizontalGroup(
            trxMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trxMasukPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trxMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(trxMasukLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trxMasukPanelLayout.createSequentialGroup()
                        .addComponent(jmlTrxMasukLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(trxMasukBlnThnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        trxMasukPanelLayout.setVerticalGroup(
            trxMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trxMasukPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trxMasukLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(trxMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlTrxMasukLabel)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(trxMasukBlnThnLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        trxKeluarPanel1.setBackground(new java.awt.Color(102, 102, 102));

        trxKeluarLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        trxKeluarLabel1.setForeground(new java.awt.Color(255, 255, 255));
        trxKeluarLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon-transaksi-retur.png"))); // NOI18N
        trxKeluarLabel1.setText("Transaksi Retur");

        jmlTrxReturLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jmlTrxReturLabel.setForeground(new java.awt.Color(255, 255, 255));
        jmlTrxReturLabel.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Transaksi");

        trxReturBlnThnLabel.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        trxReturBlnThnLabel.setForeground(new java.awt.Color(255, 255, 255));
        trxReturBlnThnLabel.setText("bulan tahun");

        javax.swing.GroupLayout trxKeluarPanel1Layout = new javax.swing.GroupLayout(trxKeluarPanel1);
        trxKeluarPanel1.setLayout(trxKeluarPanel1Layout);
        trxKeluarPanel1Layout.setHorizontalGroup(
            trxKeluarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trxKeluarPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trxKeluarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(trxKeluarLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trxKeluarPanel1Layout.createSequentialGroup()
                        .addComponent(jmlTrxReturLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addComponent(trxReturBlnThnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        trxKeluarPanel1Layout.setVerticalGroup(
            trxKeluarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trxKeluarPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trxKeluarLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(trxKeluarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlTrxReturLabel)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(trxReturBlnThnLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        stokAlertPanel.setBackground(new java.awt.Color(204, 0, 0));

        lblAlertStok.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblAlertStok.setForeground(new java.awt.Color(255, 255, 255));
        lblAlertStok.setText("Peringatan!");

        javax.swing.GroupLayout stokAlertPanelLayout = new javax.swing.GroupLayout(stokAlertPanel);
        stokAlertPanel.setLayout(stokAlertPanelLayout);
        stokAlertPanelLayout.setHorizontalGroup(
            stokAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stokAlertPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAlertStok)
                .addContainerGap(582, Short.MAX_VALUE))
        );
        stokAlertPanelLayout.setVerticalGroup(
            stokAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stokAlertPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAlertStok)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGrafikTerlaris.setLayout(new java.awt.BorderLayout());

        stokPanel1.setBackground(new java.awt.Color(197, 210, 64));
        stokPanel1.setPreferredSize(new java.awt.Dimension(313, 133));

        stokLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        stokLabel1.setForeground(new java.awt.Color(255, 255, 255));
        stokLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/icon-pengguna.png"))); // NOI18N
        stokLabel1.setText("Pelanggan Aktif");

        jmlPelangganLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jmlPelangganLabel.setForeground(new java.awt.Color(255, 255, 255));
        jmlPelangganLabel.setText("0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pelanggan");

        trxMasukBlnThnLabel1.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        trxMasukBlnThnLabel1.setForeground(new java.awt.Color(255, 255, 255));
        trxMasukBlnThnLabel1.setText("setahun terakhir");

        javax.swing.GroupLayout stokPanel1Layout = new javax.swing.GroupLayout(stokPanel1);
        stokPanel1.setLayout(stokPanel1Layout);
        stokPanel1Layout.setHorizontalGroup(
            stokPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stokPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stokPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stokPanel1Layout.createSequentialGroup()
                        .addGroup(stokPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stokLabel1)
                            .addGroup(stokPanel1Layout.createSequentialGroup()
                                .addComponent(jmlPelangganLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)))
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(trxMasukBlnThnLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        stokPanel1Layout.setVerticalGroup(
            stokPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stokPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stokLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(stokPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jmlPelangganLabel)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(trxMasukBlnThnLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGrafikBatang.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout menuUtamaPanelLayout = new javax.swing.GroupLayout(menuUtamaPanel);
        menuUtamaPanel.setLayout(menuUtamaPanelLayout);
        menuUtamaPanelLayout.setHorizontalGroup(
            menuUtamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUtamaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuUtamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuUtamaPanelLayout.createSequentialGroup()
                        .addComponent(stokPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(stokPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(trxMasukPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(trxKeluarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(trxKeluarPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuUtamaPanelLayout.createSequentialGroup()
                        .addGroup(menuUtamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jumboMenuUtama)
                            .addComponent(stokAlertPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(menuUtamaPanelLayout.createSequentialGroup()
                                .addComponent(panelGrafikTerlaris, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelGrafikBatang, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menuUtamaPanelLayout.setVerticalGroup(
            menuUtamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUtamaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jumboMenuUtama)
                .addGap(18, 18, 18)
                .addGroup(menuUtamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(trxKeluarPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trxKeluarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trxMasukPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stokPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stokPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(menuUtamaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGrafikTerlaris, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                    .addComponent(panelGrafikBatang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(stokAlertPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
        );

        mainPanel.add(menuUtamaPanel, "card2");

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel headerTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jmlPelangganLabel;
    private javax.swing.JLabel jmlStokLabel;
    private javax.swing.JLabel jmlTrxKeluarLabel;
    private javax.swing.JLabel jmlTrxMasukLabel;
    private javax.swing.JLabel jmlTrxReturLabel;
    private javax.swing.JLabel jumboMenuUtama;
    private javax.swing.JLabel lblAlertStok;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPopupMenu menuDataMaster;
    private javax.swing.JMenuItem menuPelanggan;
    private javax.swing.JMenuItem menuProduk;
    private javax.swing.JMenuItem menuSupplier;
    private javax.swing.JPanel menuUtamaPanel;
    private javax.swing.JPanel panelGrafikBatang;
    private javax.swing.JPanel panelGrafikTerlaris;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel stokAlertPanel;
    private javax.swing.JLabel stokLabel;
    private javax.swing.JLabel stokLabel1;
    private javax.swing.JPanel stokPanel;
    private javax.swing.JPanel stokPanel1;
    private javax.swing.JLabel trxKeluarBlnThnLabel;
    private javax.swing.JLabel trxKeluarLabel;
    private javax.swing.JLabel trxKeluarLabel1;
    private javax.swing.JPanel trxKeluarPanel;
    private javax.swing.JPanel trxKeluarPanel1;
    private javax.swing.JLabel trxMasukBlnThnLabel;
    private javax.swing.JLabel trxMasukBlnThnLabel1;
    private javax.swing.JLabel trxMasukLabel;
    private javax.swing.JPanel trxMasukPanel;
    private javax.swing.JLabel trxReturBlnThnLabel;
    // End of variables declaration//GEN-END:variables

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin logout?",
            "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            SessionManager.getInstance().clearSession();

            new FormLogin().setVisible(true);
            this.dispose();
        }
    }
    
    // =============================================
    // SIDEBAR HELPER METHODS
    // =============================================

    private JButton activeButton = null;

//    private JButton createMenuButton(String text) {
//        JButton btn = new JButton(text);
//        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
//        btn.setPreferredSize(new Dimension(220, 45));
//        btn.setHorizontalAlignment(SwingConstants.LEFT);
//        btn.setBorderPainted(true);
//        btn.setFocusPainted(false);
//        btn.setContentAreaFilled(true);
//        btn.setBackground(new Color(44, 62, 80));
//        btn.setForeground(Color.WHITE);
//        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
//        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btn.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
//
//        // Penting! Agar BoxLayout tidak geser ke kanan
//        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        btn.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseEntered(java.awt.event.MouseEvent e) {
//                if (btn != activeButton)
//                    btn.setBackground(new Color(52, 73, 94));
//            }
//            @Override
//            public void mouseExited(java.awt.event.MouseEvent e) {
//                if (btn != activeButton)
//                    btn.setBackground(new Color(44, 62, 80));
//            }
//        });
//        return btn;
//    }
    
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setPreferredSize(new Dimension(220, 45));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(true);
        btn.setBackground(new Color(44, 62, 80));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMargin(new Insets(0, 0, 0, 0));

        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 153, 153)),
            BorderFactory.createEmptyBorder(0, 15, 0, 0)
        ));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (btn != activeButton)
                    btn.setBackground(new Color(52, 73, 94));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (btn != activeButton)
                    btn.setBackground(new Color(44, 62, 80));
            }
        });
        return btn;
    }

//    private JButton createSubMenuButton(String text) {
//        JButton btn = createMenuButton(text);
//        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        btn.setBackground(new Color(36, 52, 66));
//        btn.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
//        btn.setPreferredSize(new Dimension(220, 38));
//        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
//        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        return btn;
//    }
    
    private JButton createSubMenuButton(String text) {
        JButton btn = createMenuButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setBackground(new Color(36, 52, 66));
        btn.setPreferredSize(new Dimension(220, 38));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMargin(new Insets(0, 0, 0, 0));

        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 153, 153)),
            BorderFactory.createEmptyBorder(0, 35, 0, 0)
        ));
        return btn;
    }

    private JLabel createMenuLabel(String text) {
        JLabel lbl = new JLabel(text.toUpperCase());
        lbl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        lbl.setPreferredSize(new Dimension(220, 30));
        lbl.setForeground(new Color(149, 165, 166));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setBorder(BorderFactory.createEmptyBorder(8, 15, 2, 0));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JPanel createSubMenuPanel(JButton... buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 102, 102));
        panel.setVisible(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(btn);
        }
        return panel;
    }

    private void setActiveButton(JButton btn) {
        if (activeButton != null) {
            activeButton.setBackground(new Color(44, 62, 80));
        }
        activeButton = btn;
        btn.setBackground(new Color(0, 102, 102));
    }
    
    private void showPanel (String panelName) {
//        cardLayout.show(mainPanel, panelName);
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        String judulHalaman = "FiveFrost Delights - " + panelName;
        this.setTitle(judulHalaman);
        cl.show(mainPanel, panelName);
        loadDashboardData();
        tampilGrafikTerlaris();
        tampilGrafikBatang();
    }

    private void loadSessionData() {
        SessionManager session = SessionManager.getInstance();

        lblUserName.setText(session.getUsername());
        lblSubtitle.setText(session.getRole());
        jumboMenuUtama.setText("Halo " + session.getUsername() + ", Selamat Bekerja!");
    }
    
    private void loadDashboardData() {
        try {
            LocalDate sekarang = LocalDate.now();
            Locale indo = new Locale("id", "ID");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM yyyy", indo);
            
            Connection conn = KoneksiDB.getKoneksi();

            String sqlStok = "SELECT COUNT(*) AS total_produk FROM master_produk;";
            Statement stmtStok = conn.createStatement();
            ResultSet rsStok = stmtStok.executeQuery(sqlStok);
            if (rsStok.next()) {
                int totalStok = rsStok.getInt("total_produk");
                jmlStokLabel.setText(String.valueOf(totalStok));
            }
            
            String sqlPelangganAktif = "SELECT COUNT(DISTINCT id_pelanggan) AS total_pelanggan_aktif FROM transaksi_keluar WHERE tanggal >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR)";
            Statement stmtPelanggan = conn.createStatement();
            ResultSet rsPelanggan = stmtPelanggan.executeQuery(sqlPelangganAktif);
            if (rsPelanggan.next()) {
                jmlPelangganLabel.setText(rsPelanggan.getString("total_pelanggan_aktif"));
            }

            String sqlMasuk = "SELECT COALESCE(SUM(qty_masuk), 0) AS total_masuk FROM transaksi_masuk WHERE MONTH(tanggal) = MONTH(CURRENT_DATE()) AND YEAR(tanggal) = YEAR(CURRENT_DATE());";
            Statement stmtMasuk = conn.createStatement();
            ResultSet rsMasuk = stmtMasuk.executeQuery(sqlMasuk);
            if (rsMasuk.next()) {
                int totalMasuk = rsMasuk.getInt("total_masuk");
                jmlTrxMasukLabel.setText(String.valueOf(totalMasuk));
                trxMasukBlnThnLabel.setText(String.valueOf(sekarang.format(format)));
            }

            String sqlKeluar = "SELECT COALESCE(SUM(qty_keluar), 0) AS total_keluar FROM transaksi_keluar WHERE MONTH(tanggal) = MONTH(CURRENT_DATE()) AND YEAR(tanggal) = YEAR(CURRENT_DATE());";
            Statement stmtKeluar = conn.createStatement();
            ResultSet rsKeluar = stmtKeluar.executeQuery(sqlKeluar);
            if (rsKeluar.next()) {
                int totalKeluar = rsKeluar.getInt("total_keluar");
                jmlTrxKeluarLabel.setText(String.valueOf(totalKeluar));
                trxKeluarBlnThnLabel.setText(String.valueOf(sekarang.format(format)));
            }

            String sqlRetur = "SELECT COALESCE(SUM(qty_retur), 0) AS total_retur FROM transaksi_retur WHERE MONTH(tanggal) = MONTH(CURRENT_DATE()) AND YEAR(tanggal) = YEAR(CURRENT_DATE());";
            Statement stmtRetur = conn.createStatement();
            ResultSet rsRetur = stmtRetur.executeQuery(sqlRetur);
            if (rsRetur.next()) {
                int totalRetur = rsRetur.getInt("total_retur");
                jmlTrxReturLabel.setText(String.valueOf(totalRetur));
                trxReturBlnThnLabel.setText(String.valueOf(sekarang.format(format)));
            }
            
            String sqlCekStok = "SELECT COUNT(*) AS jumlah_kritis FROM master_produk WHERE stok <= 10";
            Statement stmtCekStok = conn.createStatement();
            ResultSet rsCekStok = stmtCekStok.executeQuery(sqlCekStok);

            if (rsCekStok.next()) {
                int jumlahKritis = rsCekStok.getInt("jumlah_kritis");

                if (jumlahKritis > 0) {
                    lblAlertStok.setText("PERHATIAN: " + jumlahKritis + " produk stok menipis! Segera order ke Supplier!");
                    lblAlertStok.setForeground(Color.WHITE);
                    stokAlertPanel.setVisible(true);
                } else {
                    stokAlertPanel.setVisible(false);
                }
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Gagal memuat data dashboard: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void tampilGrafikTerlaris() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            java.sql.Connection conn = koneksi.KoneksiDB.getKoneksi();
            String sql = "SELECT p.nama_produk, SUM(t.qty_keluar) AS total_terjual " +
                         "FROM transaksi_keluar t " +
                         "JOIN master_produk p ON t.id_produk = p.id_produk " +
                         "GROUP BY p.nama_produk " +
                         "ORDER BY total_terjual DESC " +
                         "LIMIT 5";

            java.sql.Statement stat = conn.createStatement();
            java.sql.ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                String namaProduk = rs.getString("nama_produk");
                int total = rs.getInt("total_terjual");

                dataset.setValue(namaProduk + " (" + total + ")", total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Top 5 Produk Paling Laris",
                dataset,                    
                true,                       
                true,                       
                false                       
        );

        chart.setBackgroundPaint(java.awt.Color.WHITE);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(panelGrafikTerlaris.getWidth(), panelGrafikTerlaris.getHeight()));

        panelGrafikTerlaris.removeAll();
        panelGrafikTerlaris.add(chartPanel, BorderLayout.CENTER);
        panelGrafikTerlaris.validate();
    }
    
    private void tampilGrafikBatang() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            java.sql.Connection conn = koneksi.KoneksiDB.getKoneksi();
            java.sql.Statement stat = conn.createStatement();
            
            String sqlMasukLalu = "SELECT COALESCE(SUM(qty_masuk), 0) AS total FROM transaksi_masuk WHERE MONTH(tanggal) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(tanggal) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH)";
            java.sql.ResultSet rsMasukLalu = stat.executeQuery(sqlMasukLalu);
            if (rsMasukLalu.next()) {
                dataset.addValue(rsMasukLalu.getInt("total"), "Barang Masuk", "Bulan Lalu");
            }

            String sqlKeluarLalu = "SELECT COALESCE(SUM(qty_keluar), 0) AS total FROM transaksi_keluar WHERE MONTH(tanggal) = MONTH(CURRENT_DATE() - INTERVAL 1 MONTH) AND YEAR(tanggal) = YEAR(CURRENT_DATE() - INTERVAL 1 MONTH)";
            java.sql.ResultSet rsKeluarLalu = stat.executeQuery(sqlKeluarLalu);
            if (rsKeluarLalu.next()) {
                dataset.addValue(rsKeluarLalu.getInt("total"), "Barang Keluar", "Bulan Lalu");
            }

            String sqlMasukIni = "SELECT COALESCE(SUM(qty_masuk), 0) AS total FROM transaksi_masuk WHERE MONTH(tanggal) = MONTH(CURRENT_DATE()) AND YEAR(tanggal) = YEAR(CURRENT_DATE())";
            java.sql.ResultSet rsMasukIni = stat.executeQuery(sqlMasukIni);
            if (rsMasukIni.next()) {
                dataset.addValue(rsMasukIni.getInt("total"), "Barang Masuk", "Bulan Ini");
            }

            String sqlKeluarIni = "SELECT COALESCE(SUM(qty_keluar), 0) AS total FROM transaksi_keluar WHERE MONTH(tanggal) = MONTH(CURRENT_DATE()) AND YEAR(tanggal) = YEAR(CURRENT_DATE())";
            java.sql.ResultSet rsKeluarIni = stat.executeQuery(sqlKeluarIni);
            if (rsKeluarIni.next()) {
                dataset.addValue(rsKeluarIni.getInt("total"), "Barang Keluar", "Bulan Ini");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Perbandingan Produk Masuk vs Keluar",
                "Periode",                      
                "Total Kuantitas",              
                dataset,                        
                PlotOrientation.VERTICAL,       
                true,                           
                true,                           
                false                           
        );

//        chart.setBackgroundPaint(java.awt.Color.WHITE);
        org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
        org.jfree.chart.renderer.category.BarRenderer renderer = (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new java.awt.Color(0, 153, 153));

        renderer.setSeriesPaint(1, new java.awt.Color(204, 0, 51));

        renderer.setDrawBarOutline(false);
        
        plot.setBackgroundPaint(java.awt.Color.WHITE);
        plot.setDomainGridlinePaint(java.awt.Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(java.awt.Color.LIGHT_GRAY);
        

        ChartPanel chartPanel = new ChartPanel(chart);
        panelGrafikBatang.removeAll();
        panelGrafikBatang.add(chartPanel, BorderLayout.CENTER);
        panelGrafikBatang.validate();
    }
    
    private void startAutoRefresh() {
        int intervalWaktu = 60000;

        timerDashboard = new javax.swing.Timer(intervalWaktu, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loadDashboardData();
            }
        });
        timerDashboard.start();
    }
}
