package com.gamemodeswitch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GamemodeScreen extends Screen {
    public GamemodeScreen() {
        super(Component.translatable("gamemodeswitch.title"));
    }

    @Override
    protected void init() {
        int w = 120, h = 20, gap = 6;
        int x = this.width / 2 - w / 2;
        int y = this.height / 2 - (3 * h + 2 * gap) / 2;

        addMode("creative", x, y, w, h);
        addMode("survival", x, y + h + gap, w, h);
        addMode("spectator", x, y + 2 * (h + gap), w, h);
    }

    private void addMode(String mode, int x, int y, int w, int h) {
        addRenderableWidget(Button.builder(
                Component.translatable("gamemodeswitch." + mode),
                b -> {
                    Minecraft mc = Minecraft.getInstance();
                    if (mc.player != null) {
                        mc.player.connection.sendCommand("gamemode " + mode);
                    }
                    onClose();
                }).bounds(x, y, w, h).build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
