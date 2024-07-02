/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.goals;

import containers.Services;
import exceptions.ResponseException;
import views.MainFrame;
import views.users.EditSelf;
import interfaces.Contextable;
import models.Goal;
import models.PerformanceScore;
import services.GoalService;
import utilities.Context;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Alternative;
import models.Criterion;
import models.PairwiseComparison;
import java.util.Map;

/**
 *
 * @author arfanxn
 */
public class Edit extends javax.swing.JPanel implements Contextable {

    private Context ctx;
    private GoalService service;
    private Goal goal;
    private List<Map<String, String>> rankings;

    /**
     * Creates new form Index
     *
     * @param ctx
     */
    public Edit(Context ctx) {
        this.ctx = ctx;
        this.service = Services.initGoal();
        initComponents();
        
        this.fetchGoal();
        this.fetchGoalRankingization();
        this.fillFields();
        this.loadCriteriaTable();
        this.loadAlternativesTable();
        this.loadPssTable();
        this.loadPwcsTable();
        this.loadRankingsTable();
    }

    private void fetchGoal() {
        try {
            this.ctx = this.service.show(this.ctx);
            this.ctx.remove("message");
            this.goal = this.ctx.<Goal>get("goal");
        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }
    
    private void fetchGoalRankingization() {
        try {
            this.ctx = this.service.rankingization(this.ctx);
            this.ctx.remove("message");
            this.rankings = this.ctx.get("rankings");
        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }

    private void fillFields() {
        this.titleField.setText(this.goal.getTitle());
        this.descriptionArea.setText(this.goal.getDescription());
    }
    
    private void loadCriteriaTable () {
        List<Criterion> criteria = this.goal.getCriteria();
        int criteriaLength = criteria.size();
        
        String[] tableColumnNames = new String[]{
                "name", 
                "is benefit"
        };
        Object[][] data = new Object[criteriaLength][tableColumnNames.length];
     
        for (int i = 0; i < criteriaLength; i++) {
            Criterion criterion = criteria.get(i);
            data[criterion.getIndex()][0] = criterion.getName();
            data[criterion.getIndex()][1] = criterion.getImpactType();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumnNames);
        this.criteriaTable.setModel(tableModel);
    }
    
    private void loadAlternativesTable() {
        List<Alternative> alternatives = this.goal.getAlternatives();
        int alternativesLength = alternatives.size();

        String[] tableColumnNames = new String[]{"name"};
        Object[][] data = new Object[alternativesLength][tableColumnNames.length];

        for (int i = 0; i < alternativesLength; i++) {
            Alternative alternative = alternatives.get(i);
            data[alternative.getIndex()][0] = alternative.getName();
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumnNames);
        this.alternativesTable.setModel(tableModel);
    }
    
    
    private void loadPssTable() {
        List<Criterion> criteria = this.goal.getCriteria();
        List<Alternative> alternatives = this.goal.getAlternatives();
        List<PerformanceScore> pss = this.goal.getPerformanceScores();
        int pssLength = pss.size();

        String[] tableColumnNames = new String[criteria.size() + 1];
        Object[][] data = new Object[alternatives.size() + 1][criteria.size() + 1];
        data[0][0] = "A/C";
        tableColumnNames[0] = "A/C";
        criteria.forEach((criterion) -> {
            int columnIndex = criterion.getIndex() + 1;
            tableColumnNames[columnIndex] = criterion.getName();
            data[0][columnIndex] = criterion.getName();
        });
        alternatives.forEach((alternative) -> {
            int rowIndex = alternative.getIndex() + 1;
            data[rowIndex][0] = alternative.getName();
        });
        for (int i = 0; i < pssLength; i++) {
            PerformanceScore ps = pss.get(i);
            int rowIndex = ps.getAlternative().getIndex() + 1;
            int columnIndex = ps.getCriterion().getIndex() + 1;
            data[rowIndex][columnIndex] = String.valueOf(ps.getValue());
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumnNames);
        this.pssTable.setModel(tableModel);
    }
    
    private void loadPwcsTable() {
        List<Criterion> criteria = this.goal.getCriteria();
        List<PairwiseComparison> pwcs = this.goal.getPairwiseComparisons();
        int pwcsLength = pwcs.size();

        String[] tableColumnNames = new String[criteria.size() + 1];
        Object[][] data = new Object[criteria.size() + 1][criteria.size() + 1];
        data[0][0] = "C";
        tableColumnNames[0] = "A/C";
        criteria.forEach((criterion) -> {
            int index = criterion.getIndex() + 1;
            tableColumnNames[index] = criterion.getName();
            data[0][index] = criterion.getName();
            data[index][0] = criterion.getName();
        });
        for (int i = 0; i < pwcsLength; i++) {
            PairwiseComparison pwc = pwcs.get(i);
            int rowIndex = pwc.getPrimaryCriterion().getIndex() + 1;
            int columnIndex = pwc.getSecondaryCriterion().getIndex() + 1;
            data[rowIndex][columnIndex] = String.valueOf(pwc.getValue());
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumnNames);
        this.pwcsTable.setModel(tableModel);
    }
    
    private void loadRankingsTable() {
        var rs = this.rankings ;
        int rsLength = this.goal.getAlternatives().size();

        String[] tableColumnNames = new String[]{"name", "index", "score", "rank"};
        Object[][] data = new Object[rsLength][tableColumnNames.length];

        for (int i = 0; i < rsLength; i++) {
            var r = rs.get(i);
            data[i][0] = r.get("alternative_name");
            data[i][1] = r.get("alternative_index");
            data[i][2] = r.get("alternative_score");
            data[i][3] = r.get("alternative_rank");
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, tableColumnNames);
        this.rankingsTable.setModel(tableModel);
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navbarPanel = new javax.swing.JPanel();
        dashboardLabel = new javax.swing.JLabel();
        accountLabel = new javax.swing.JLabel();
        goalTabbedPane = new javax.swing.JTabbedPane();
        goalPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        criteriaPanel = new javax.swing.JPanel();
        criteriaScrollPane = new javax.swing.JScrollPane();
        criteriaTable = new javax.swing.JTable();
        alternativesPanel = new javax.swing.JPanel();
        alternativesScrollPane = new javax.swing.JScrollPane();
        alternativesTable = new javax.swing.JTable();
        pssPanel = new javax.swing.JPanel();
        pssScrollPane = new javax.swing.JScrollPane();
        pssTable = new javax.swing.JTable();
        pwcsPanel = new javax.swing.JPanel();
        pwcsScrollPane = new javax.swing.JScrollPane();
        pwcsTable = new javax.swing.JTable();
        rankingizationPanel = new javax.swing.JPanel();
        rankingizationScrollPane = new javax.swing.JScrollPane();
        rankingsTable = new javax.swing.JTable();
        saveBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(59, 130, 246));
        setPreferredSize(new java.awt.Dimension(700, 500));

        navbarPanel.setBackground(new java.awt.Color(245, 158, 11));

        dashboardLabel.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(255, 255, 255));
        dashboardLabel.setText("Dashboard");
        dashboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseClicked(evt);
            }
        });

        accountLabel.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        accountLabel.setForeground(new java.awt.Color(255, 255, 255));
        accountLabel.setText("Account");
        accountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout navbarPanelLayout = new javax.swing.GroupLayout(navbarPanel);
        navbarPanel.setLayout(navbarPanelLayout);
        navbarPanelLayout.setHorizontalGroup(
            navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountLabel)
                .addGap(14, 14, 14)
                .addComponent(dashboardLabel)
                .addGap(25, 25, 25))
        );
        navbarPanelLayout.setVerticalGroup(
            navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navbarPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(navbarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboardLabel)
                    .addComponent(accountLabel))
                .addGap(14, 14, 14))
        );

        goalTabbedPane.setBackground(new java.awt.Color(245, 158, 11));
        goalTabbedPane.setForeground(new java.awt.Color(255, 255, 255));
        goalTabbedPane.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N

        goalPanel.setBackground(new java.awt.Color(245, 158, 11));
        goalPanel.setPreferredSize(new java.awt.Dimension(508, 280));

        emailLabel.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Title");

        titleField.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N

        descriptionLabel.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        descriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionLabel.setText("Description");

        descriptionArea.setColumns(20);
        descriptionArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        descriptionArea.setRows(5);
        descriptionScrollPane.setViewportView(descriptionArea);

        javax.swing.GroupLayout goalPanelLayout = new javax.swing.GroupLayout(goalPanel);
        goalPanel.setLayout(goalPanelLayout);
        goalPanelLayout.setHorizontalGroup(
            goalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goalPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(goalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(goalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(goalPanelLayout.createSequentialGroup()
                            .addComponent(descriptionLabel)
                            .addGap(406, 406, 406))
                        .addGroup(goalPanelLayout.createSequentialGroup()
                            .addComponent(emailLabel)
                            .addGap(472, 472, 472)))
                    .addGroup(goalPanelLayout.createSequentialGroup()
                        .addGroup(goalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titleField)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                        .addGap(25, 25, 25))))
        );
        goalPanelLayout.setVerticalGroup(
            goalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goalPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        goalTabbedPane.addTab("Goal", goalPanel);

        criteriaPanel.setBackground(new java.awt.Color(245, 158, 11));
        criteriaPanel.setLayout(new java.awt.BorderLayout());

        criteriaScrollPane.setBackground(new java.awt.Color(245, 158, 11));

        criteriaTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        criteriaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        criteriaTable.setToolTipText("");
        criteriaTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        criteriaTable.setDropMode(javax.swing.DropMode.ON);
        criteriaTable.setRowHeight(25);
        criteriaTable.setSelectionBackground(java.awt.Color.blue);
        criteriaTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        criteriaTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        criteriaTable.setShowGrid(true);
        criteriaTable.setSurrendersFocusOnKeystroke(true);
        criteriaScrollPane.setViewportView(criteriaTable);

        criteriaPanel.add(criteriaScrollPane, java.awt.BorderLayout.CENTER);

        goalTabbedPane.addTab("Criteria", criteriaPanel);

        alternativesPanel.setBackground(new java.awt.Color(245, 158, 11));
        alternativesPanel.setLayout(new java.awt.BorderLayout());

        alternativesScrollPane.setBackground(new java.awt.Color(245, 158, 11));

        alternativesTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        alternativesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        alternativesTable.setToolTipText("");
        alternativesTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        alternativesTable.setDropMode(javax.swing.DropMode.ON);
        alternativesTable.setRowHeight(25);
        alternativesTable.setSelectionBackground(java.awt.Color.blue);
        alternativesTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        alternativesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        alternativesTable.setShowGrid(true);
        alternativesTable.setSurrendersFocusOnKeystroke(true);
        alternativesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alternativesTableMouseClicked(evt);
            }
        });
        alternativesScrollPane.setViewportView(alternativesTable);

        alternativesPanel.add(alternativesScrollPane, java.awt.BorderLayout.CENTER);

        goalTabbedPane.addTab("Alternatives", alternativesPanel);

        pssPanel.setBackground(new java.awt.Color(245, 158, 11));
        pssPanel.setLayout(new java.awt.BorderLayout());

        pssScrollPane.setBackground(new java.awt.Color(245, 158, 11));

        pssTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        pssTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        pssTable.setToolTipText("");
        pssTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pssTable.setDropMode(javax.swing.DropMode.ON);
        pssTable.setRowHeight(25);
        pssTable.setSelectionBackground(java.awt.Color.blue);
        pssTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        pssTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pssTable.setShowGrid(true);
        pssTable.setSurrendersFocusOnKeystroke(true);
        pssTable.setTableHeader(null);
        pssScrollPane.setViewportView(pssTable);

        pssPanel.add(pssScrollPane, java.awt.BorderLayout.CENTER);

        goalTabbedPane.addTab("Pss", pssPanel);

        pwcsPanel.setBackground(new java.awt.Color(245, 158, 11));
        pwcsPanel.setLayout(new java.awt.BorderLayout());

        pwcsScrollPane.setBackground(new java.awt.Color(245, 158, 11));

        pwcsTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        pwcsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        pwcsTable.setToolTipText("");
        pwcsTable.setColumnSelectionAllowed(true);
        pwcsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pwcsTable.setDropMode(javax.swing.DropMode.ON);
        pwcsTable.setRowHeight(25);
        pwcsTable.setSelectionBackground(java.awt.Color.blue);
        pwcsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        pwcsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pwcsTable.setShowGrid(true);
        pwcsTable.setSurrendersFocusOnKeystroke(true);
        pwcsTable.setTableHeader(null);
        pwcsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pwcsTableKeyReleased(evt);
            }
        });
        pwcsScrollPane.setViewportView(pwcsTable);

        pwcsPanel.add(pwcsScrollPane, java.awt.BorderLayout.CENTER);

        goalTabbedPane.addTab("Pwcs", pwcsPanel);

        rankingizationPanel.setBackground(new java.awt.Color(245, 158, 11));
        rankingizationPanel.setLayout(new java.awt.BorderLayout());

        rankingizationScrollPane.setBackground(new java.awt.Color(245, 158, 11));

        rankingsTable.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        rankingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "index", "score", "rank"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rankingsTable.setToolTipText("");
        rankingsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rankingsTable.setDropMode(javax.swing.DropMode.ON);
        rankingsTable.setRowHeight(25);
        rankingsTable.setSelectionBackground(java.awt.Color.blue);
        rankingsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        rankingsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        rankingsTable.setShowGrid(true);
        rankingsTable.setSurrendersFocusOnKeystroke(true);
        rankingizationScrollPane.setViewportView(rankingsTable);

        rankingizationPanel.add(rankingizationScrollPane, java.awt.BorderLayout.CENTER);

        goalTabbedPane.addTab("Rankingization", rankingizationPanel);

        saveBtn.setBackground(new java.awt.Color(245, 158, 11));
        saveBtn.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbarPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveBtn)
                    .addComponent(goalTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navbarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(goalTabbedPane)
                .addGap(18, 18, 18)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pwcsTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwcsTableKeyReleased
        int row = pwcsTable.getSelectedRow();
        int column = pwcsTable.getSelectedColumn();
        
        Double value = Double.valueOf((String) this.pwcsTable.getModel().getValueAt(row, column));
        Double mirroredValue = 1 / value;
        
        pwcsTable.getModel().setValueAt(mirroredValue, column, row);
    }//GEN-LAST:event_pwcsTableKeyReleased

    private void alternativesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alternativesTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_alternativesTableMouseClicked

    private void accountLabelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_accountLabelMouseClicked
        MainFrame.getInstance().setComponent(new EditSelf(this.ctx));
    }// GEN-LAST:event_accountLabelMouseClicked

    private void dashboardLabelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dashboardLabelMouseClicked
        MainFrame.getInstance().setComponent(new Index(this.ctx));
    }// GEN-LAST:event_dashboardLabelMouseClicked

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveBtnActionPerformed
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        Goal goal = this.goal; 
        List<Criterion> criteria = goal.getCriteria();
        List<Alternative> alternatives = goal.getAlternatives();
        List<PerformanceScore> pss = goal.getPerformanceScores();
        List<PairwiseComparison> pwcs = goal.getPairwiseComparisons();
        
        goal.setTitle(this.titleField.getText());
        goal.setDescription(this.descriptionArea.getText());
        
        alternatives = alternatives.stream().map((Alternative alternative) -> {
            String name = String.valueOf(this.alternativesTable.getModel().getValueAt(alternative.getIndex(), 0));
            alternative.setName(name);
            return alternative;
        }).toList();
        
        criteria = criteria.stream().map((Criterion criterion) -> {
            String name1 = String.valueOf(Edit.this.criteriaTable.getModel().getValueAt(criterion.getIndex(), 0));
            Boolean impactType = Boolean.valueOf(String.valueOf(Edit.this.criteriaTable.getModel().getValueAt(criterion.getIndex(), 1)));
            criterion.setName(name1);
            criterion.setImpactType(impactType);
            return criterion;
        }).toList();
        
        pss = pss.stream().map((PerformanceScore ps) -> {
            int row = ps.getAlternative().getIndex() + 1;
            int column = ps.getCriterion().getIndex() + 1;
            Double value = Double.valueOf(String.valueOf(this.pssTable.getModel().getValueAt(row, column)));
            ps.setValue(value);
            return ps;
        }).toList();
        
        pwcs = pwcs.stream().map((PairwiseComparison pwc) -> {
            int row = pwc.getPrimaryCriterion().getIndex() + 1;
            int column = pwc.getSecondaryCriterion().getIndex() + 1;
            Double value = Double.valueOf(String.valueOf(this.pwcsTable.getModel().getValueAt(row, column)));
            pwc.setValue(value);
            return pwc;
        }).toList();
        
        goal.setAlternatives(alternatives);
        goal.setCriteria(criteria);
        goal.setPerformanceScores(pss);
        goal.setPairwiseComparisons(pwcs);
        this.goal = goal;
        
        this.ctx.put("body", goal);

        try {
            this.service.update(this.ctx);
            helpers.Alert.message(this, ctx, null);
            this.fetchGoalRankingization();
            this.loadRankingsTable();
        } catch (ResponseException e) {
            helpers.Alert.message(this, e.getMessage(), null);
        }
        
    }// GEN-LAST:event_saveBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountLabel;
    private javax.swing.JPanel alternativesPanel;
    private javax.swing.JScrollPane alternativesScrollPane;
    private javax.swing.JTable alternativesTable;
    private javax.swing.JPanel criteriaPanel;
    private javax.swing.JScrollPane criteriaScrollPane;
    private javax.swing.JTable criteriaTable;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel goalPanel;
    private javax.swing.JTabbedPane goalTabbedPane;
    private javax.swing.JPanel navbarPanel;
    private javax.swing.JPanel pssPanel;
    private javax.swing.JScrollPane pssScrollPane;
    private javax.swing.JTable pssTable;
    private javax.swing.JPanel pwcsPanel;
    private javax.swing.JScrollPane pwcsScrollPane;
    private javax.swing.JTable pwcsTable;
    private javax.swing.JPanel rankingizationPanel;
    private javax.swing.JScrollPane rankingizationScrollPane;
    private javax.swing.JTable rankingsTable;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
}
