package tartan;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.border.Border;

class ToolTipLookAndFeel extends NimbusLookAndFeel
{
    protected void initSystemColorDefaults(UIDefaults table)
    {
        super.initSystemColorDefaults(table);
        table.put("info", new ColorUIResource(0, 247, 200));
    }

    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);

        Border border = BorderFactory.createLineBorder(new Color(76,79,83));
        table.put("ToolTip.border", border);
    }
}
