/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.util.Date;

/**
 *
 * @author 101794
 */
public class HomeView extends javax.swing.JFrame {
    //declare ProjectModel variables
    int project_id=0, user_id, county_id, activities, personnel, progress;
    String title, sponsor, pm, budget, actual_cost;
    Date start_date, end_date;
    ProjectModel project = new ProjectModel();

    /**
     * Creates new form Home
     */
    public HomeView() {
        initComponents();
        Utils.getDate();
        if(Utils.loggedIn) {
            welcomeLabel.setText("Welcome "+Utils.user.getFullname());
            projectHeaderLabel.setText("Project Details: "+ Utils.user.getCounty());
        }
        //show counties
        CountyModel counties = new CountyModel();
        counties.showList(countyStatsTable);
        //show projects
        project.showList(projectsTable);
        //set projects table row sorter
        projectsTable = Utils.setRowSorter(projectsTable, searchTxt);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        dashLabel = new javax.swing.JLabel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        projectPanel = new javax.swing.JPanel();
        projectHeaderLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        sponsorLabel = new javax.swing.JLabel();
        pmLabel = new javax.swing.JLabel();
        activitiesLabel = new javax.swing.JLabel();
        titleTxt = new javax.swing.JTextField();
        sponsorTxt = new javax.swing.JTextField();
        pmTxt = new javax.swing.JTextField();
        addProjectBtn = new javax.swing.JButton();
        projectsScrollPane = new javax.swing.JScrollPane();
        projectsTable = new javax.swing.JTable();
        updateProjectBtn = new javax.swing.JButton();
        deleteProjectBtn = new javax.swing.JButton();
        budgetLabel = new javax.swing.JLabel();
        costLabel = new javax.swing.JLabel();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        personelLabel = new javax.swing.JLabel();
        progressLabel = new javax.swing.JLabel();
        progressTxt = new javax.swing.JComboBox<>();
        activitiesTxt = new javax.swing.JFormattedTextField();
        budgetTxt = new javax.swing.JFormattedTextField();
        costTxt = new javax.swing.JFormattedTextField();
        startTxt = new javax.swing.JFormattedTextField();
        personnelTxt = new javax.swing.JFormattedTextField();
        endTxt = new javax.swing.JFormattedTextField();
        refreshBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        searchLabel = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        disbursePanel = new javax.swing.JPanel();
        countyStatsPanel = new javax.swing.JPanel();
        countyStatsScrollPane = new javax.swing.JScrollPane();
        countyStatsTable = new javax.swing.JTable();
        statusLabel = new javax.swing.JLabel();
        homeMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        logoutMI = new javax.swing.JMenuItem();
        accountMenu = new javax.swing.JMenu();
        accountMI = new javax.swing.JMenuItem();
        expensesMI = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CGIS - County Government Information System");

        dashLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        dashLabel.setText("Dashboard");

        projectHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        projectHeaderLabel.setText("Project Details");

        titleLabel.setText("Title *");

        sponsorLabel.setText("Project Sponsor *");

        pmLabel.setText("Project Manager *");

        activitiesLabel.setText("No. of Activities");

        addProjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-plus-16.png"))); // NOI18N
        addProjectBtn.setText("Add");
        addProjectBtn.setToolTipText("Add new project");
        addProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProjectBtnActionPerformed(evt);
            }
        });

        projectsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Sponsor", "Project Manager", "Budget", "Start Date", "End Date", "Progress", "Created By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        projectsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projectsTableMouseClicked(evt);
            }
        });
        projectsScrollPane.setViewportView(projectsTable);

        updateProjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-update-16.png"))); // NOI18N
        updateProjectBtn.setText("Update");
        updateProjectBtn.setToolTipText("Update project");
        updateProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProjectBtnActionPerformed(evt);
            }
        });

        deleteProjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-trash-16.png"))); // NOI18N
        deleteProjectBtn.setText("Delete");
        deleteProjectBtn.setToolTipText("Delete project");
        deleteProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProjectBtnActionPerformed(evt);
            }
        });

        budgetLabel.setText("Budget *");

        costLabel.setText("Actual Cost");

        startDateLabel.setText("Start Date *");

        endDateLabel.setText("End Date *");

        personelLabel.setText("Personnel");

        progressLabel.setText("Progress");

        progressTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        activitiesTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        budgetTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        costTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        startTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        startTxt.setToolTipText("Date Format yyyy-MM-dd e.g. 2018-03-23");

        personnelTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        endTxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        endTxt.setToolTipText("Date Format yyyy-MM-dd e.g. 2018-03-23");

        refreshBtn.setText("Refresh");
        refreshBtn.setToolTipText("Refresh the table data");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        searchLabel.setText("Search:");

        searchBtn.setText("Search");

        javax.swing.GroupLayout projectPanelLayout = new javax.swing.GroupLayout(projectPanel);
        projectPanel.setLayout(projectPanelLayout);
        projectPanelLayout.setHorizontalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(projectHeaderLabel)
                        .addGroup(projectPanelLayout.createSequentialGroup()
                            .addComponent(addProjectBtn)
                            .addGap(18, 18, 18)
                            .addComponent(updateProjectBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                            .addComponent(deleteProjectBtn))
                        .addGroup(projectPanelLayout.createSequentialGroup()
                            .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addComponent(sponsorLabel)
                                .addComponent(pmLabel)
                                .addComponent(activitiesLabel)
                                .addComponent(budgetLabel)
                                .addComponent(costLabel)
                                .addComponent(startDateLabel)
                                .addComponent(endDateLabel)
                                .addComponent(personelLabel)
                                .addComponent(progressLabel))
                            .addGap(35, 35, 35)
                            .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(titleTxt)
                                .addComponent(sponsorTxt)
                                .addComponent(pmTxt)
                                .addComponent(activitiesTxt)
                                .addComponent(budgetTxt)
                                .addComponent(costTxt)
                                .addComponent(startTxt)
                                .addComponent(personnelTxt)
                                .addGroup(projectPanelLayout.createSequentialGroup()
                                    .addComponent(progressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(endTxt))))
                    .addGroup(projectPanelLayout.createSequentialGroup()
                        .addComponent(resetBtn)
                        .addGap(18, 18, 18)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectsScrollPane)
                    .addGroup(projectPanelLayout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchBtn))))
        );
        projectPanelLayout.setVerticalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectHeaderLabel)
                    .addComponent(searchLabel)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(projectPanelLayout.createSequentialGroup()
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleLabel)
                            .addComponent(titleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sponsorLabel)
                            .addComponent(sponsorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pmLabel)
                            .addComponent(pmTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(activitiesLabel)
                            .addComponent(activitiesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(budgetLabel)
                            .addComponent(budgetTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(costLabel)
                            .addComponent(costTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startDateLabel)
                            .addComponent(startTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(endDateLabel)
                            .addComponent(endTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(projectPanelLayout.createSequentialGroup()
                                .addComponent(personelLabel)
                                .addGap(24, 24, 24)
                                .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(progressLabel)
                                    .addComponent(progressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(personnelTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshBtn)
                            .addComponent(resetBtn))
                        .addGap(18, 18, 18)
                        .addGroup(projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addProjectBtn)
                            .addComponent(updateProjectBtn)
                            .addComponent(deleteProjectBtn)))
                    .addComponent(projectsScrollPane))
                .addContainerGap())
        );

        mainTabbedPane.addTab("Projects ", new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-project-50.png")), projectPanel); // NOI18N

        javax.swing.GroupLayout disbursePanelLayout = new javax.swing.GroupLayout(disbursePanel);
        disbursePanel.setLayout(disbursePanelLayout);
        disbursePanelLayout.setHorizontalGroup(
            disbursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );
        disbursePanelLayout.setVerticalGroup(
            disbursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Disbursements ", new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-cash-in-hand-50.png")), disbursePanel); // NOI18N

        countyStatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "County Name", "Former Province", "Area", "Population", "Capital"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        countyStatsScrollPane.setViewportView(countyStatsTable);

        javax.swing.GroupLayout countyStatsPanelLayout = new javax.swing.GroupLayout(countyStatsPanel);
        countyStatsPanel.setLayout(countyStatsPanelLayout);
        countyStatsPanelLayout.setHorizontalGroup(
            countyStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, countyStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(countyStatsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                .addContainerGap())
        );
        countyStatsPanelLayout.setVerticalGroup(
            countyStatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(countyStatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(countyStatsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("County Stats ", new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-statistics-50.png")), countyStatsPanel); // NOI18N

        fileMenu.setText("File");

        logoutMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        logoutMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-logout-rounded-left-16.png"))); // NOI18N
        logoutMI.setText("Logout");
        logoutMI.setToolTipText("Logout");
        logoutMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMIActionPerformed(evt);
            }
        });
        fileMenu.add(logoutMI);

        homeMenuBar.add(fileMenu);

        accountMenu.setText("Profile");

        accountMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-user-16.png"))); // NOI18N
        accountMI.setText("My Account");
        accountMenu.add(accountMI);

        expensesMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-receive-cash-16.png"))); // NOI18N
        expensesMI.setText("My Expenses");
        accountMenu.add(expensesMI);

        homeMenuBar.add(accountMenu);

        helpMenu.setText("Help");

        aboutMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        aboutMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Project/images/icons8-help-16.png"))); // NOI18N
        aboutMI.setText("About CGIS");
        aboutMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMIActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMI);

        homeMenuBar.add(helpMenu);

        setJMenuBar(homeMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(welcomeLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dashLabel)
                                .addGap(18, 18, 18)
                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dashLabel)
                    .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMIActionPerformed
        if(Utils.showDialog("Are you sure you want to logout?") == 0) {
            // logout user...Close Main App and show the login form
            Utils.logout();
            java.awt.EventQueue.invokeLater(() -> {
                new LoginView().setVisible(true);
            });
            this.dispose();
        }
    }//GEN-LAST:event_logoutMIActionPerformed

    private void aboutMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMIActionPerformed
        // Show about this project
        java.awt.EventQueue.invokeLater(() -> {
            new HelpView().setVisible(true);
        });
        
    }//GEN-LAST:event_aboutMIActionPerformed

    private void addProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProjectBtnActionPerformed
        // Add a new project
        if (dataValid("project") && ( Utils.showDialog("Add this project to the database?") == 0)) {
            project = new ProjectModel(county_id, user_id, activities, personnel, 
                    progress, title, sponsor, pm, budget, actual_cost, start_date,end_date);
            if (project.add()) {
                Utils.showStatus(statusLabel, "New project added successfully", 10, "success");
                //refresh the table
                project.showList(projectsTable);
                //reset project form fields
                resetFields("project");
            }
        }
    }//GEN-LAST:event_addProjectBtnActionPerformed

    private void updateProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProjectBtnActionPerformed
        // Update a project
        //first check if a row is selected
        if(projectsTable.getSelectionModel().isSelectionEmpty()) {
            Utils.showStatus(statusLabel, "Error! Cannot update project. No row selected", 10, "error");
            Utils.showDialog("Select a project", "Error");
        } else {
            if (dataValid("project") && ( Utils.showDialog("Update this project?") == 0)) {
                project = new ProjectModel(county_id, user_id, activities, personnel, 
                        progress, title, sponsor, pm, budget, actual_cost, start_date,end_date);
                if (project.update(project_id)) {
                    Utils.showStatus(statusLabel, "Project updated successfully", 10, "success");
                    //refresh the table
                    project.showList(projectsTable);
                    //reset project form fields
                    resetFields("project");
                }            
            }
        }
    }//GEN-LAST:event_updateProjectBtnActionPerformed

    private void projectsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projectsTableMouseClicked
        // show the clicked row in table on the projects form textfields
        int index = projectsTable.getSelectedRow(); //get the index of selected row
        javax.swing.table.TableModel model = projectsTable.getModel(); //get the table data model
        //get project id
        project_id = Integer.parseInt(model.getValueAt(index, 0).toString());
        //get the project
        project = project.find(project_id);
        //load the textfields
        titleTxt.setText(project.getTitle());
        sponsorTxt.setText(project.getSponsor());
        pmTxt.setText(project.getManager());
        activitiesTxt.setValue(project.getActivities());
        budgetTxt.setText(project.getBudget());
        costTxt.setText(project.getActualCost());
        personnelTxt.setValue(project.getPersonnel());
        progressTxt.setSelectedItem(Integer.toString(project.getProgress()));
        startTxt.setValue(project.getStartDate());
        endTxt.setValue(project.getEndDate());
    }//GEN-LAST:event_projectsTableMouseClicked

    private void deleteProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProjectBtnActionPerformed
        // Delete a project from the database
        //first check if a row is selected
        if(projectsTable.getSelectionModel().isSelectionEmpty()) {
            Utils.showStatus(statusLabel, "Error! Cannot delete project. No row selected", 10, "error");
            Utils.showDialog("Select a project", "Error");
        } else if (Utils.showDialog("Are you sure you want to delete the project with ID="+project_id+"?") == 0){
            if (project.delete(project_id)) {
                Utils.showStatus(statusLabel, "Successfully deleted project from the database", 10, "success");
                //refresh the table and reset the form fields
                project.showList(projectsTable);
                resetFields("project");
            }
        }
    }//GEN-LAST:event_deleteProjectBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // refresh the table
        project.showList(projectsTable);
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // reset projects form fields
        resetFields("project");
    }//GEN-LAST:event_resetBtnActionPerformed

    private boolean dataValid(String type) {
        String actString="", perString="";
        if("project".equals(type)) {
            user_id = Utils.user.getID();
            county_id = Utils.user.getCountyCode();
            title = titleTxt.getText().trim();
            sponsor = sponsorTxt.getText().trim();
            pm = pmTxt.getText().trim();
            actString = activitiesTxt.getText().trim();
            budget = budgetTxt.getText().trim();
            actual_cost = costTxt.getText().trim();
            perString = personnelTxt.getText().trim();
            progress = Integer.parseInt(progressTxt.getSelectedItem().toString());
            
            if (title.equals("") || sponsor.equals("") || pm.equals("") || budget.equals("") || startTxt.getText().equals("") || endTxt.getText().equals("")) {
                Utils.showStatus(statusLabel, "You have errors in your form. Kindly correct", 10, "error");
                Utils.showDialog("Enter a value for each compulsory field", "Error");
                return false;
            }
            
        } else if ("disburse".equals(type)) {
            
        }
        start_date = (Date)startTxt.getValue();
        end_date = (Date)endTxt.getValue();
        activities = (actString.equals(""))? 0 : Integer.parseInt(actString);
        personnel = (perString.equals(""))? 0 : Integer.parseInt(perString);
        return true;
    }
    //reset form fields
    private void resetFields(String form) {
        if (form.equals("project")) {
            titleTxt.setText("");
            sponsorTxt.setText("");
            pmTxt.setText("");
            activitiesTxt.setValue(null);
            budgetTxt.setValue(null);
            costTxt.setValue(null);
            personnelTxt.setValue(null);
            progressTxt.setSelectedItem("0");
            startTxt.setValue(null);
            endTxt.setValue(null);
        }
        else if (form.equals("disburse")) {
            
        }
    }
    
    /**
     * @param args the command line arguments
     *
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form *
        java.awt.EventQueue.invokeLater(() -> {
            new HomeView().setVisible(true);
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMI;
    private javax.swing.JMenuItem accountMI;
    private javax.swing.JMenu accountMenu;
    private javax.swing.JLabel activitiesLabel;
    private javax.swing.JFormattedTextField activitiesTxt;
    private javax.swing.JButton addProjectBtn;
    private javax.swing.JLabel budgetLabel;
    private javax.swing.JFormattedTextField budgetTxt;
    private javax.swing.JLabel costLabel;
    private javax.swing.JFormattedTextField costTxt;
    private javax.swing.JPanel countyStatsPanel;
    private javax.swing.JScrollPane countyStatsScrollPane;
    private javax.swing.JTable countyStatsTable;
    private javax.swing.JLabel dashLabel;
    private javax.swing.JButton deleteProjectBtn;
    private javax.swing.JPanel disbursePanel;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JFormattedTextField endTxt;
    private javax.swing.JMenuItem expensesMI;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar homeMenuBar;
    private javax.swing.JMenuItem logoutMI;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JLabel personelLabel;
    private javax.swing.JFormattedTextField personnelTxt;
    private javax.swing.JLabel pmLabel;
    private javax.swing.JTextField pmTxt;
    private javax.swing.JLabel progressLabel;
    private javax.swing.JComboBox<String> progressTxt;
    private javax.swing.JLabel projectHeaderLabel;
    private javax.swing.JPanel projectPanel;
    private javax.swing.JScrollPane projectsScrollPane;
    private javax.swing.JTable projectsTable;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JLabel sponsorLabel;
    private javax.swing.JTextField sponsorTxt;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JFormattedTextField startTxt;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTxt;
    private javax.swing.JButton updateProjectBtn;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
