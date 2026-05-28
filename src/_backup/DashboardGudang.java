/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author rakha
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardGudang extends JFrame {

    private JPanel panelUtama;
    private CardLayout cardLayout;

    public DashboardGudang() {
        setTitle("Aplikasi Inventory Gudang");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ==========================================
        // 1. HEADER (Atas)
        // ==========================================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(10, 30, 80)); // Warna biru gelap
        headerPanel.setPreferredSize(new Dimension(0, 60));

        JLabel titleLabel = new JLabel("  Toko Besi Griya Warna - Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel timeLabel = new JLabel("23:45:39  "); // Bisa diganti dengan Timer dinamis
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(timeLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // ==========================================
        // 2. SIDEBAR MENU (Kiri)
        // ==========================================
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.WHITE);
        sidebarPanel.setPreferredSize(new Dimension(200, 0));

        // Profil User dummy
        JLabel lblUser = new JLabel("Administrator", SwingConstants.CENTER);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUser.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        sidebarPanel.add(lblUser);

        // Tombol Menu
        JButton btnDashboard = createMenuButton("Dashboard");
        JButton btnMaster = createMenuButton("Master Data");
        JButton btnTransaksi = createMenuButton("Transaksi");
        JButton btnLaporan = createMenuButton("Report");

        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnMaster);
        sidebarPanel.add(btnTransaksi);
        sidebarPanel.add(btnLaporan);
        
        add(sidebarPanel, BorderLayout.WEST);

        // ==========================================
        // 3. MAIN CONTENT (Tengah) dengan CardLayout
        // ==========================================
        cardLayout = new CardLayout();
        panelUtama = new JPanel(cardLayout);
        panelUtama.setBackground(new Color(230, 230, 235)); // Abu-abu terang

        // Buat View untuk masing-masing menu
        JPanel viewDashboard = createDashboardView();
        JPanel viewMaster = createBlankView("Halaman Master Data");
        JPanel viewTransaksi = createBlankView("Halaman Transaksi");
        JPanel viewLaporan = createBlankView("Halaman Laporan");

        // Masukkan view ke dalam panelUtama (CardLayout)
        panelUtama.add(viewDashboard, "Dashboard");
        panelUtama.add(viewMaster, "Master Data");
        panelUtama.add(viewTransaksi, "Transaksi");
        panelUtama.add(viewLaporan, "Report");

        add(panelUtama, BorderLayout.CENTER);

        // ==========================================
        // 4. EVENT LISTENER UNTUK MENU
        // ==========================================
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                cardLayout.show(panelUtama, command); // Mengganti tampilan berdasarkan nama Card
            }
        };

        btnDashboard.addActionListener(menuListener);
        btnMaster.addActionListener(menuListener);
        btnTransaksi.addActionListener(menuListener);
        btnLaporan.addActionListener(menuListener);
    }

    // Method bantuan untuk membuat tombol menu yang seragam
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    // Method untuk membuat view Dashboard (berisi kartu ringkasan)
    private JPanel createDashboardView() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panel.setBackground(new Color(220, 224, 230));

        // Membuat Kartu Ringkasan (Meniru gambar kedua)
        panel.add(createSummaryCard("Jumlah Barang", "200", new Color(100, 100, 255)));
        panel.add(createSummaryCard("Barang Masuk", "300", new Color(100, 255, 100)));
        panel.add(createSummaryCard("Barang Keluar", "100", new Color(255, 100, 100)));

        return panel;
    }

    // Method bantuan untuk mendesain Kartu Ringkasan
    private JPanel createSummaryCard(String title, String value, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(250, 120));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(accentColor, 2));

        JLabel lblValue = new JLabel(value, SwingConstants.RIGHT);
        lblValue.setFont(new Font("Arial", Font.BOLD, 36));
        lblValue.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 20));

        JLabel lblTitle = new JLabel(title, SwingConstants.RIGHT);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTitle.setForeground(Color.GRAY);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 20));

        card.add(lblValue, BorderLayout.CENTER);
        card.add(lblTitle, BorderLayout.SOUTH);

        return card;
    }

    // Method bantuan untuk membuat view kosong (untuk menu lain)
    private JPanel createBlankView(String text) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(220, 224, 230));
        panel.add(new JLabel(text));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardGudang().setVisible(true);
        });
    }
}
