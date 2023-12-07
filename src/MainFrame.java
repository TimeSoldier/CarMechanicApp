import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class MainFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom GUI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        JPanel searchPanel = new JPanel(new GridLayout(1,6,10,10));

        JRadioButton modelRadioButton = new JRadioButton("Model");
        JRadioButton brandRadioButton = new JRadioButton("Brand");
        JRadioButton registrationRadioButton = new JRadioButton("Registration");
        JTextField searchInputBox = new JTextField(15);
        JButton searchButton = new JButton("Search");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(modelRadioButton);
        radioGroup.add(brandRadioButton);
        radioGroup.add(registrationRadioButton);

        searchPanel.add(new JLabel("Select Option:"));
        searchPanel.add(modelRadioButton);
        searchPanel.add(brandRadioButton);
        searchPanel.add(registrationRadioButton);
        searchPanel.add(new JLabel("Search Term:"));
        searchPanel.add(searchInputBox);
        searchPanel.add(searchButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(searchPanel, gbc);


        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setBounds(100, 50, 50, 50);
        outputScrollPane.setSize(600, 100);
        frame.add(outputScrollPane);
        frame.setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 7, 20, 20));
        JButton clearButton = new JButton("Clear");
        JButton loadButton = new JButton("Load");
        JTextField id = new JTextField();
        inputPanel.add(id);
        inputPanel.add(new JLabel("ID"));
        JTextField brand = new JTextField();
        inputPanel.add(brand);
        inputPanel.add(new JLabel("brand"));
        JTextField model = new JTextField();
        inputPanel.add(model);
        inputPanel.add(new JLabel("model"));
        inputPanel.add(loadButton);
        JTextField registration = new JTextField();
        inputPanel.add(registration);
        inputPanel.add(new JLabel("registration"));
        JTextField production_year = new JTextField(); 
        inputPanel.add(production_year);
        inputPanel.add(new JLabel("production year"));
        JTextField owner = new JTextField();
        inputPanel.add(owner);
        inputPanel.add(new JLabel("owner"));
        inputPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(inputPanel, gbc);

        JPanel buttonCar = new JPanel(new GridLayout(1, 4, 80, 10));
        JButton addCar = new JButton("Add a car");
        buttonCar.add(addCar);
        JButton redactCar = new JButton("Redact a car");
        buttonCar.add(redactCar);
        JButton deleteCar = new JButton("Delete a Car");
        buttonCar.add(deleteCar);
        JButton listCar = new JButton("List all cars");
        buttonCar.add(listCar);


        gbc.gridx=0;
        gbc.gridy=8;
        mainPanel.add(buttonCar,gbc);

        JPanel inputPanelServices = new JPanel(new GridLayout(2, 7, 10, 10));
        JButton clearButtonServices = new JButton("Clear");
        JButton loadButtonServices = new JButton("Load");
        JTextField idServices = new JTextField();
        inputPanelServices.add(idServices, gbc);
        inputPanelServices.add(new JLabel("ID"));
        JTextField name = new JTextField();
        inputPanelServices.add(name, gbc);
        inputPanelServices.add(new JLabel("name"));
        inputPanelServices.add(loadButtonServices);
        JTextField description = new JTextField(); 
        inputPanelServices.add(description, gbc);
        inputPanelServices.add(new JLabel("description"));
        JTextField price = new JTextField();
        inputPanelServices.add(price, gbc);
        inputPanelServices.add(new JLabel("price"));
        inputPanelServices.add(clearButtonServices);

        gbc.gridx = 0;
        gbc.gridy = 12;
        mainPanel.add(inputPanelServices, gbc);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 80, 10));
        JButton addService = new JButton("Add a service");
        buttonPanel.add(addService);
        JButton redactService = new JButton("Redact a service");
        buttonPanel.add(redactService);
        JButton deleteService = new JButton("Delete a service");
        buttonPanel.add(deleteService);
        JButton listServices = new JButton("List all services");
        buttonPanel.add(listServices);

        gbc.gridx=0;
        gbc.gridy=14;
        mainPanel.add(buttonPanel, gbc);


        JPanel inputPanelAppointments = new JPanel(new GridLayout(1, 7, 10, 10));
        JButton clearButtonAppointments = new JButton("Clear");
        JButton loadButtonAppointments = new JButton("Load");

        JTextField idAppointments = new JTextField();
        inputPanelAppointments.add(idAppointments); 
        inputPanelAppointments.add(new JLabel("ID")); 

        JTextField refCarId = new JTextField();
        inputPanelAppointments.add(refCarId);
        inputPanelAppointments.add(new JLabel("car id"));

        JTextField refServiceId = new JTextField();
        inputPanelAppointments.add(refServiceId);
        inputPanelAppointments.add(new JLabel("service id"));

        inputPanelAppointments.add(loadButtonAppointments);
        gbc.gridx = 0;
        gbc.gridy = 16;
        mainPanel.add(inputPanelAppointments, gbc);

        JPanel inputPanelAppointments2 = new JPanel(new GridLayout(1,5,10,10));
        JTextField timeAp = new JTextField();
        inputPanelAppointments2.add(timeAp);
        inputPanelAppointments2.add(new JLabel("time"));

        JTextField status = new JTextField();
        inputPanelAppointments2.add(status);
        inputPanelAppointments2.add(new JLabel("status"));

        inputPanelAppointments2.add(clearButtonAppointments);

        gbc.gridx = 0;
        gbc.gridy = 18; 
        mainPanel.add(inputPanelAppointments2, gbc);


        JPanel buttonAppointment = new JPanel(new GridLayout(1,4,50,10 ));
        JButton serviceCar = new JButton("Service a car");
        buttonAppointment.add(serviceCar);
        JButton redactAppointment = new JButton("Redact an appointment");
        buttonAppointment.add(redactAppointment);
        JButton declineAppointment = new JButton("Decline appointment");
        buttonAppointment.add(declineAppointment);
        JButton listAppointments = new JButton("List all appointments");
        buttonAppointment.add(listAppointments);


        gbc.gridx = 0;
        gbc.gridy = 24;
        mainPanel.add(buttonAppointment,gbc);

        frame.add(mainPanel);
        frame.setVisible(true);

        searchButton.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                outputTextArea.setText("");
                String searchBy = modelRadioButton.isSelected() ? "model" :
                                  brandRadioButton.isSelected() ? "brand" :
                                  registrationRadioButton.isSelected() ? "registration" : "";
                
                String searchValue = searchInputBox.getText();
                if (!searchBy.isEmpty() && !searchValue.isEmpty()) 
                {
                    CarMethods.Search(searchBy, searchValue, outputTextArea);
                }
                 else 
                {
                    outputTextArea.setText("Please select a search criteria and enter a value.");
                }
            }
        });
        
        clearButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                brand.setText("");
                model.setText("");
                registration.setText("");
                production_year.setText("");
                owner.setText("");
            }
        });

        loadButton.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int idLoad = Integer.valueOf(id.getText());
                CarMethods.Load(idLoad, outputTextArea);

                id.setText(CarMethods.loadResult[0]);
                brand.setText(CarMethods.loadResult[1]);
                model.setText(CarMethods.loadResult[2]);
                registration.setText(CarMethods.loadResult[3]);
                production_year.setText(CarMethods.loadResult[4]);
                owner.setText(CarMethods.loadResult[5]);
            }
        });
        
        redactCar.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                CarMethods.Redact(Integer.valueOf(id.getText()), brand.getText(), model.getText(), registration.getText(),
                                  Integer.valueOf(production_year.getText()), owner.getText(), outputTextArea);

            }
            
        });

        deleteCar.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                CarMethods.Delete(Integer.valueOf(id.getText()), outputTextArea);
            }
        });

        addCar.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                CarMethods.Add(brand.getText(), model.getText(), registration.getText(),
                                  Integer.valueOf(production_year.getText()), owner.getText(), outputTextArea);
            }
        });

        listCar.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                CarMethods.List(outputTextArea);
            }
            
        });

            clearButtonServices.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                idServices.setText("");
                name.setText("");
                description.setText("");
                price.setText("");
            }
        });

        loadButtonServices.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int idLoadService = Integer.valueOf(idServices.getText());
                ServiceMethods.Load(idLoadService, outputTextArea);

                idServices.setText(ServiceMethods.loadResultService[0]);
                name.setText(ServiceMethods.loadResultService[1]);
                description.setText(ServiceMethods.loadResultService[2]);
                price.setText(ServiceMethods.loadResultService[3]);

            }
        });
        
        redactService.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                ServiceMethods.Redact(Integer.valueOf(idServices.getText()), name.getText(), description.getText(), new BigDecimal(price.getText()), outputTextArea);

            }
            
        });

        deleteService.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                ServiceMethods.Delete(Integer.valueOf(idServices.getText()), outputTextArea);
            }
        });

        addService.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                ServiceMethods.Add(name.getText(), description.getText(), new BigDecimal(price.getText()),outputTextArea);
            }
        });

        listServices.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                ServiceMethods.List(outputTextArea);
            }
            
        });

        
                clearButtonAppointments.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                idAppointments.setText("");
                refCarId.setText("");
                refServiceId.setText("");
                timeAp.setText("");
                status.setText("");
            }
        });

        loadButtonAppointments.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int idLoadAppointment = Integer.valueOf(idAppointments.getText());
                AppointmentsMethods.Load(idLoadAppointment, outputTextArea);

                idAppointments.setText(AppointmentsMethods.loadResultAppointments[0]);
                refCarId.setText(AppointmentsMethods.loadResultAppointments[1]);
                refServiceId.setText(AppointmentsMethods.loadResultAppointments[2]);
                timeAp.setText(AppointmentsMethods.loadResultAppointments[3]);
                status.setText(AppointmentsMethods.loadResultAppointments[4]);
            }
        });
        
        redactAppointment.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                AppointmentsMethods.Redact(Integer.valueOf(idAppointments.getText()),Integer.valueOf(refCarId.getText()), Integer.valueOf(refServiceId.getText()),  timeAp.getText(), status.getText(), outputTextArea);

            }
            
        });

        declineAppointment.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                AppointmentsMethods.Delete(Integer.valueOf(idAppointments.getText()), outputTextArea);
            }
        });

        serviceCar.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                AppointmentsMethods.Add(Integer.valueOf(refCarId.getText()),Integer.valueOf(refServiceId.getText()), timeAp.getText(), status.getText(),outputTextArea);
            }
        });

        listAppointments.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                AppointmentsMethods.List(outputTextArea);
            }
            
        });

    }
    
    
}
