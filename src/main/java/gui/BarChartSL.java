package gui;

import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class BarChartSL extends JPanel
{
    private int histogramHeight = 120;
    private int barWidth = 30;
    private int barGap = 5;

    private JPanel barPanel;
    private JPanel labelPanel;
    
    private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public int getHistogramHeight() {
		return histogramHeight;
	}

	public void setHistogramHeight(int histogramHeight) {
		this.histogramHeight = histogramHeight;
	}

	public int getBarWidth() {
		return barWidth;
	}

	public void setBarWidth(int barWidth) {
		this.barWidth = barWidth;
	}

	private List<Bar> bars = new ArrayList<Bar>();

    public BarChartSL()
    {
        setBorder( new EmptyBorder(1, 1, 1, 1) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, double dem, Color color)
    {
        Bar bar = new Bar(label, dem, color);
        bars.add( bar );
    }

    public void layoutHistogram() throws ParseException
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        double maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setFont(new Font("Tahoma", Font.PLAIN, 10));
            double barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private double value;
        private Color color;

        public Bar(String label, double dem, Color color)
        {
            this.label = label;
            this.value = dem;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public double getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private double height;

        public ColorIcon(Color color, int width, double barHeight)
        {
            this.color = color;
            this.width = width;
            this.height = barHeight;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return (int) height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, (int) height);
            g.setColor(Color.GRAY);
            g.fillRect(x + width - shadow, y + shadow, shadow, (int) (height - shadow));
        }
    }

    private static void createAndShowGUI()
    {
    	BarChartSL panel = new BarChartSL();
        panel.addHistogramColumn("A", 350, Color.RED);
        panel.addHistogramColumn("B", 690, Color.YELLOW);
        /*panel.addHistogramColumn("C", 510, Color.BLUE);
        panel.addHistogramColumn("D", 570, Color.ORANGE);
        panel.addHistogramColumn("E", 180, Color.MAGENTA);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);
        panel.addHistogramColumn("F", 504, Color.CYAN);*/
        try {
			panel.layoutHistogram();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        JFrame frame = new JFrame("Histogram Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}