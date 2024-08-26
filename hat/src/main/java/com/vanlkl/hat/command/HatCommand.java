package com.vanlkl.hat.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class HatCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal("hat")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(HatCommand::execute));
    }

    private static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayerOrThrow();
        ItemStack itemInHand = player.getMainHandStack();

        if (itemInHand.isEmpty()) {
            source.sendFeedback(() -> Text.literal("成功将'空气'戴在头上!"), false);
            return 0;
        }

        ItemStack itemInHeadSlot = player.getInventory().armor.get(3);
        if (!itemInHeadSlot.isEmpty()) {
            source.sendFeedback(() -> Text.literal("你已经戴了一个头盔了!"), false);
            return 0;
        }

        player.getInventory().armor.set(3, itemInHand.copy());
        player.getMainHandStack().decrement(1);

        return Command.SINGLE_SUCCESS;
    }
}
