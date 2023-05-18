package gui;

import javax.swing.*;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Image;

import utilities.EnumContainer.Color;;

public class View extends JFrame implements ActionListener
{
    //								* FIELDS *

    private static final long serialVersionUID = 1L;
    private static RaceBuilder builder = RaceBuilder.getInstance();;
    private static ArrayList<Racer> racers;
    private JTextField arena_length_text, max_racers_text, max_speed_text, acceleration_text, racer_name_text;
    private JComboBox<String> arenas_combo, racers_combo, colors_combo;
    private int arena_length = 1000, arena_height = 700, max_racers = 8, racers_number = 0;
    private String chosen_arena = null;
    private Arena arena = null;
    private ImageIcon racers_images[] = null;
    private JFrame show_info = null;
    private boolean race_started = false, race_finished = false;

    //								* METHODS *

    public static void main(String[] args) { new View(); }

    public View()
    {
        super("Race");
        this.setContentPane(mainFrame());
        this.pack();
        Dimension resulution = Toolkit.getDefaultToolkit().getScreenSize();
        int x_point = (int) ( ( resulution.getWidth() - getWidth() ) / 2  );
        int y_point = (int) ( ( resulution.getHeight() - getHeight() ) / 2);
        this.setLocation(x_point, y_point);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void changeImage(String image_path, int arena_length)
    {
        try
        {
            if (arena_length > 1000) {
                // Adjust the screen resolution based on the arena length
                Dimension resolution = new Dimension(arena_length, this.getHeight());
                this.setSize(resolution);
                this.setLocationRelativeTo(null); // Center the window on the screen
            }
            //this.selected_arena_image = ImageIO.read(new File(image_path));
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {

            case "Build arena":
                //if the race started and not finished than show error message and return till the next event
                if (this.race_started && !this.race_finished)
                {
                    JOptionPane.showMessageDialog(this, "Race started! Please wait.");
                    return;
                }
                try
                {
                    this.arena_length = Integer.parseInt(this.arena_length_text.getText());
                    this.max_racers = Integer.parseInt(this.max_racers_text.getText());

                    if (this.arena_length < 100 || this.arena_length > 3000 || this.max_racers <= 0 || this.max_racers > 20)
                        throw new Exception();
                }
                catch (Exception ex) //show message and return till next event
                {
                    JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
                    return;
                }

                this.racers_number = 0;
                this.race_started = false;
                this.race_finished = false;

                //setting the new height
                int new_height = (this.max_racers + 1) * 60;
                if (new_height > 700)
                    this.arena_height = new_height;
                else
                    this.arena_height = 700;

                //init racers
                this.racers = new ArrayList<>();
                this.racers_images = new ImageIcon[this.max_racers];
                this.chosen_arena = (String) this.arenas_combo.getSelectedItem();
                try
                {
                    if (this.chosen_arena.equals("AerialArena"))
                        arena = builder.buildArena("game.arenas.air.AerialArena", this.arena_length, this.max_racers);

                    else if (this.chosen_arena.equals("LandArena"))
                        arena = builder.buildArena("game.arenas.land.LandArena", this.arena_length, this.max_racers);

                    else if (this.chosen_arena.equals("NavalArena"))
                        arena = builder.buildArena("game.arenas.naval.NavalArena", this.arena_length, this.max_racers);

                }
                catch (Exception ex) { System.out.println(ex); }
                refresh();
                break;

            case "Add racer":
                if (this.race_finished)
                {
                    JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
                    return;
                }
                if (this.race_started)
                {
                    JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
                    return;
                }
                if (this.arena == null)
                {
                    JOptionPane.showMessageDialog(this, "Please build arena first!");
                    return;
                }
                if (this.racers_number == this.max_racers)
                {
                    JOptionPane.showMessageDialog(this, "No more racers can be added!");
                    return;
                }

                //get valuse for constructors
                String name;
                double maxSpeed;
                double acceleration;
                try
                {
                    name = this.racer_name_text.getText();
                    maxSpeed = Double.parseDouble(this.max_speed_text.getText());
                    acceleration = Double.parseDouble(this.acceleration_text.getText());
                    if (name.isEmpty() || maxSpeed <= 0 || acceleration <= 0)
                        throw new Exception("Invalid input values! Please try again.");
                }
                catch (Exception invalid_inputs_error)
                {
                    JOptionPane.showMessageDialog(this, invalid_inputs_error.getMessage());
                    return;
                }

                //setting enum color
                String racer_type = (String) this.racers_combo.getSelectedItem();
                String color = (String) this.colors_combo.getSelectedItem();
                Color color_enum = null;
                if (color.equals("Red")) color_enum = Color.RED;
                else if (color.equals("Black")) color_enum = Color.BLACK;
                else if (color.equals("Green")) color_enum = Color.GREEN;
                else if (color.equals("Blue")) color_enum = Color.BLUE;
                else if (color.equals("Yellow")) color_enum = Color.YELLOW;

                //setting class of choosen racer
                String racer_class = null;
                if (racer_type.equals("Helicopter")) racer_class = "game.racers.air.Helicopter";
                else if (racer_type.equals("Airplane")) racer_class = "game.racers.air.Airplane";
                else if (racer_type.equals("Car")) racer_class = "game.racers.land.Car";
                else if (racer_type.equals("Horse")) racer_class = "game.racers.land.Horse";
                else if (racer_type.equals("Bicycle")) racer_class = "game.racers.land.Bicycle";
                else if (racer_type.equals("SpeedBoat")) racer_class = "game.racers.naval.SpeedBoat";
                else if (racer_type.equals("RowBoat")) racer_class = "game.racers.naval.RowBoat";

                try
                {
                    Racer racer = builder.buildRacer(racer_class, name, maxSpeed, acceleration, color_enum);
                    arena.addRacer(racer);
                    arena.initRace();
                    racers.add(racer);
                }
                catch (RacerTypeException type_exception)
                {
                    JOptionPane.showMessageDialog(this, "Recer does not fit to arena! Choose another racer.");
                    return;
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }

                this.racers_images[this.racers_number] = new ImageIcon(new ImageIcon("gui/icons/" + racer_type + color + ".png").getImage()
                        .getScaledInstance(70, 70, Image.SCALE_DEFAULT));
                this.racers_number++;

                refresh();
                break;

            case "Srart race":
                if (this.arena == null || this.racers_number == 0)
                {
                    JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
                    return;
                }
                if (this.race_finished)
                {
                    JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena and add racers.");
                    return;
                }
                if (this.race_started)
                {
                    JOptionPane.showMessageDialog(this, "Race already started!");
                    return;
                }
                try
                {
                    this.race_started = true;
                    //the race started so need to start the thread
                    (new Thread()
                    {
                        public void run()
                        {
                            while (arena.hasActiveRacers())
                            {
                                try { Thread.sleep(30); }//make the thread sleep for 30MS
                                catch (InterruptedException ex) { ex.printStackTrace(); }
                                refresh();
                            }
                            refresh();
                            race_finished = true;
                        }
                    }).start();
                    //this.arena.startRace();
                } catch(Exception e1) {}
                //catch (InterruptedException ex) { ex.printStackTrace(); }
                break;

            case "Show info":
                if (this.arena == null || this.racers_number == 0)
                {
                    JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
                    return;
                }
                String[] column_names = { "Racer name", "Current speed", "Max speed", "Current X location", "Finished" };
                String[][] table = new String[this.racers_number][column_names.length];
                int i = 0;
                for (Racer racer : this.arena.getCompletedRacers())
                {
                    table[i][0] = racer.getName();
                    table[i][1] = "" + racer.getCurrentSpeed();
                    table[i][2] = "" + racer.getMaxSpeed();
                    table[i][3] = "" + racer.getCurrentLocation().getX();
                    table[i][4] = "Yes";
                    i++;
                }

                for (Racer racer : arena.getActiveRacers())
                {
                    table[i][0] = racer.getName();
                    table[i][1] = "" + racer.getCurrentSpeed();
                    table[i][2] = "" + racer.getMaxSpeed();
                    table[i][3] = "" + racer.getCurrentLocation().getX();
                    table[i][4] = "No";
                    i++;
                }

                JTable real_table = new JTable(table, column_names);
                real_table.setPreferredScrollableViewportSize(real_table.getPreferredSize());
                JScrollPane scrollPane = new JScrollPane(real_table);

                JPanel tab_panel = new JPanel();
                tab_panel.add(scrollPane);

                if (this.show_info != null)
                    this.show_info.dispose();
                this.show_info = new JFrame("Racers information");
                this.show_info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.show_info.setContentPane(tab_panel);
                this.show_info.pack();
                this.show_info.setVisible(true);

                break;
        }



	    /*if (e.getSource() instanceof JButton)
	    {
	        JButton source_button = (JButton) e.getSource();
	        if (source_button.getText().equals("Build arena"))
	        {
	            String selected_arena = (String) this.getArenasMenu().getSelectedItem();
	            int selected_index = this.getArenasMenu().getSelectedIndex();
	            if (selected_index >= 0 && selected_index < this.arena_image_paths.length)
	            {
	                String image_path = this.arena_image_paths[selected_index];
	                int arenaLength = Integer.parseInt(this.getArenaLengthTextField().getText());
	                this.changeImage(image_path, arenaLength);
	                repaint(); // Forces the left side panel to repaint and show the new image
	                try {arena = builder.buildArena(selected_arena, arena_length, Integer.parseInt(selected_arena));}
	                catch (ClassNotFoundException e1) {}
	                catch (NoSuchMethodException e1) {}
	                catch (SecurityException e1) {}
	                catch (InstantiationException e1) {}
	                catch (IllegalAccessException e1) {}
	                catch (IllegalArgumentException e1) {}
	                catch (InvocationTargetException e1) {}
	                this.arena_exist = true;
	            }
	        }

	        if (source_button.getText().equals("Add racer"))
	        {
	        	if(!this.arena_exist)
	        	{
	        		try {throw new Exception("Please build arena first and add racers!");}
	        		catch(Exception e1) { JOptionPane.showMessageDialog(this, e1.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE); }
	        	}

	        	if(this.racer_name_text.equals(""))
	        	{
	        		try {throw new Exception("Racer should have name!");}
	        		catch(Exception e2) {JOptionPane.showMessageDialog(this, e2.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);}
	        	}

	        	try
	        	{
	        	    int maxSpeed = Integer.parseInt(this.max_speed_text.getText());
	        	    int acceleration = Integer.parseInt(this.acceleration_text.getText());
	        	    if(maxSpeed < 0 || acceleration < 0)
	        	    	throw new Exception("Invalid input values! Please try again.");
	        	}
	        	catch (NumberFormatException e3) {JOptionPane.showMessageDialog(this, "Please enter numbers only!", "Message", JOptionPane.INFORMATION_MESSAGE);}
	        	catch (Exception e3) {JOptionPane.showMessageDialog(this, e3.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);}

	        	try
	        	{
	        	String selected_racer = (String) this.racers.getSelectedItem();
	        	Color selectedColor = (Color) this.colors.getSelectedItem();
	        	int arenaLength = Integer.parseInt(this.arena_length_text.getText());
        	    int maxRacers = Integer.parseInt(this.max_racers_text.getText());
	        	this.arena.addRacer(builder.buildRacer(selected_racer, this.racer_name_text.getText(), arenaLength, maxRacers, selectedColor));

	        	}
	        	catch(Exception e5) {}
	        }
	      }*/
    }


    public JPanel mainFrame()
    {
        JPanel window = new JPanel();
        window.setLayout(new BorderLayout());
        window.add(leftSidePanel(), BorderLayout.WEST);
        window.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
        window.add(rightSidePanel(), BorderLayout.EAST);
        return window;
    }

    public JPanel leftSidePanel() {
        JPanel arena_panel = new JPanel();
        arena_panel.setLayout(null);
        arena_panel.setPreferredSize(new Dimension(this.arena_length + 80, this.arena_height));
        ImageIcon image_icon1 = new ImageIcon(new ImageIcon("gui/icons/" + this.chosen_arena + ".jpg").getImage()
                .getScaledInstance(this.arena_length + 80, this.arena_height, Image.SCALE_DEFAULT));
        JLabel pic_label1 = new JLabel(image_icon1);
        pic_label1.setLocation(0, 0);
        pic_label1.setSize(arena_length + 80, arena_height);
        arena_panel.add(pic_label1);

        for (int i = 0; i < this.racers_number; i++)
        {
            JLabel pic_label2 = new JLabel(this.racers_images[i]);
            pic_label2.setLocation((int) racers.get(i).getCurrentLocation().getX() + 5, (int) racers.get(i).getCurrentLocation().getY());
            pic_label2.setSize(70, 70);
            pic_label1.add(pic_label2);
        }

        return arena_panel;
    }

    public JPanel rightSidePanel()
    {
        JPanel controls_panel = new JPanel();
        controls_panel.setLayout(null);
        controls_panel.setPreferredSize(new Dimension(140, this.arena_height));
        String[] arenas_names = {"AerialArena", "NavalArena", "LandArena"};
        this.arenas_combo = new JComboBox<>();
        int i = 0;
        for (String string : arenas_names)
        {
            this.arenas_combo.addItem(string);
            if (i == 0)
                this.arenas_combo.setSelectedItem(string);
            i++;
        }

        if (this.chosen_arena != null)
            this.arenas_combo.setSelectedItem(this.chosen_arena);

        // controlsPanel.setAlignmentX(0.0f);
        JLabel choose_arena_label = new JLabel("Choose arena:");
        controls_panel.add(choose_arena_label);
        choose_arena_label.setLocation(10, 20);
        choose_arena_label.setSize(100, 10);
        controls_panel.add(this.arenas_combo);
        this.arenas_combo.setLocation(10, 40);
        this.arenas_combo.setSize(100, 20);

        JLabel arena_length_label = new JLabel("Arena length:");
        arena_length_label.setLocation(10, 75);
        arena_length_label.setSize(100, 10);
        controls_panel.add(arena_length_label);

        this.arena_length_text = new JTextField("" + this.arena_length);
        arena_length_text.setLocation(10, 95);
        arena_length_text.setSize(100, 25);
        controls_panel.add(arena_length_text);

        JLabel max_racers_label = new JLabel("Max racers number:");
        max_racers_label.setLocation(10, 135);
        max_racers_label.setSize(150, 10);
        controls_panel.add(max_racers_label);

        this.max_racers_text = new JTextField("" + this.max_racers);
        this.max_racers_text.setLocation(10, 155);
        this.max_racers_text.setSize(100, 25);
        controls_panel.add(this.max_racers_text);

        JButton build_arena_button = new JButton("Build arena");
        build_arena_button.setLocation(10, 195);
        build_arena_button.setSize(100, 30);
        build_arena_button.addActionListener(this);
        controls_panel.add(build_arena_button);

        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setLocation(0, 240);
        sep.setSize(150, 10);
        controls_panel.add(sep);

        this.racers_combo = new JComboBox<>();
        this.racers_combo.addItem("AirPlane");
        this.racers_combo.addItem("Bicycle");
        this.racers_combo.addItem("Car");
        this.racers_combo.addItem("Helicopter");
        this.racers_combo.addItem("Horse");
        this.racers_combo.addItem("RowBoat");
        this.racers_combo.addItem("SpeedBoat");

        JLabel choose_racer_label = new JLabel("Choose racer:");
        controls_panel.add(choose_racer_label);
        choose_racer_label.setLocation(10, 260);
        choose_racer_label.setSize(100, 10);

        controls_panel.add(this.racers_combo);
        this.racers_combo.setLocation(10, 280);
        this.racers_combo.setSize(100, 20);

        JLabel choose_color_label = new JLabel("Choose color:");
        controls_panel.add(choose_color_label);
        choose_color_label.setLocation(10, 315);
        choose_color_label.setSize(100, 10);

        this.colors_combo = new JComboBox<>();
        this.colors_combo.addItem("Black");
        this.colors_combo.addItem("Green");
        this.colors_combo.addItem("Blue");
        this.colors_combo.addItem("Red");
        this.colors_combo.addItem("Yellow");
        controls_panel.add(this.colors_combo);
        this.colors_combo.setLocation(10, 335);
        this.colors_combo.setSize(100, 20);

        JLabel racer_name_label = new JLabel("Racer name:");
        racer_name_label.setLocation(10, 370);
        racer_name_label.setSize(150, 10);
        controls_panel.add(racer_name_label);

        this.racer_name_text = new JTextField("");
        this.racer_name_text.setLocation(10, 390);
        this.racer_name_text.setSize(100, 25);
        controls_panel.add(this.racer_name_text);

        JLabel max_speed_label = new JLabel("Max speed:");
        max_speed_label.setLocation(10, 425);
        max_speed_label.setSize(150, 14);
        controls_panel.add(max_speed_label);

        this.max_speed_text = new JTextField("120");
        this.max_speed_text.setLocation(10, 445);
        this.max_speed_text.setSize(100, 25);
        controls_panel.add(this.max_speed_text);

        JLabel acceleration_label = new JLabel("Acceleration:");
        acceleration_label.setLocation(10, 485);
        acceleration_label.setSize(150, 10);
        controls_panel.add(acceleration_label);

        this.acceleration_text = new JTextField("");
        this.acceleration_text.setLocation(10, 505);
        this.acceleration_text.setSize(100, 25);
        controls_panel.add(this.acceleration_text);

        JButton add_racer_button = new JButton("Add racer");
        add_racer_button.setLocation(10, 545);
        add_racer_button.setSize(100, 30);
        add_racer_button.addActionListener(this);
        controls_panel.add(add_racer_button);

        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setLocation(0, 590);
        sep2.setSize(150, 10);
        controls_panel.add(sep2);

        JButton start_race_button = new JButton("Srart race");
        start_race_button.setLocation(10, 605);
        start_race_button.setSize(100, 30);
        start_race_button.addActionListener(this);
        controls_panel.add(start_race_button);

        JButton print_info_but = new JButton("Show info");
        print_info_but.setLocation(10, 650);
        print_info_but.setSize(100, 30);
        print_info_but.addActionListener(this);
        controls_panel.add(print_info_but);

        return controls_panel;
    }

    //method for refreshing the frame and show the changes
    private void refresh()
    {
        this.setContentPane(this.mainFrame());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    public JTextField getArenaLengthTextField() { return this.arena_length_text; }
    public int getArenaHeigth() { return this.arena_height; }
    public int getArenaLength() { return this.arena_length; }
    public JComboBox<String> getArenasCombo() { return this.arenas_combo; }
    public final int getMaxRacers() { return this.max_racers; }
}