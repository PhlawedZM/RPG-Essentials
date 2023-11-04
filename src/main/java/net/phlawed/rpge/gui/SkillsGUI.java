package net.phlawed.rpge.gui;

import com.google.gson.stream.JsonReader;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.Reader;

public class SkillsGUI extends LightweightGuiDescription {

    public SkillsGUI() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 240);
        root.setInsets(Insets.ROOT_PANEL);

        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        WSprite title = new WSprite(new Identifier("rpge:textures/gui/title.png"));
        root.add(title, 0, 0, 9, 1);

        WLabel label2 = new WLabel(Text.literal(player.getName().getString()), 0xFFFFFF);
        root.add(label2, 2, 1, 1, 1);

        WToggleButton toggle1 = new WToggleButton();
        root.add(toggle1, 0,4,1,1);

        root.validate(this);

    }
}
