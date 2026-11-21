package io.github.jadimatic.jadis_mended_progression.client.tutorial;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.TutorialToast;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.client.tutorial.TutorialStepInstance;
import net.minecraft.client.tutorial.TutorialSteps;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class FindShelterTutorialStepInstance implements TutorialStepInstance {
	private static final int HINT_DELAY = 1200;
	private static final Component CRAFT_TITLE = Component.translatable("tutorial.craft_planks.title");
	private static final Component CRAFT_DESCRIPTION = Component.translatable("tutorial.craft_planks.description");
	private final Tutorial tutorial;
	private @Nullable TutorialToast toast;
	private int timeWaiting;
	public static final TutorialSteps FIND_SHELTER = TutorialSteps.valueOf("FIND_SHELTER");

	public FindShelterTutorialStepInstance(final Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	@Override
	public void tick() {//Run everything inside this every game tick.
		this.timeWaiting++;//This is a timer incrementing.
		if (!this.tutorial.isSurvival()) {//If not in Survival mode...
			this.tutorial.setStep(TutorialSteps.NONE);//Cancel tutorial by skipping to NONE.
		} else {//Otherwise...
			Minecraft minecraft = this.tutorial.getMinecraft();//???
			if (this.timeWaiting == 1) {//On the first game tick of this tutorial step running.
				LocalPlayer player = minecraft.player;//Save the player on this client to a LocalPlayer variable.
				if (player != null) {//If the player exists...

					if (player.getInventory().contains(ItemTags.PLANKS)) {
						this.tutorial.setStep(TutorialSteps.NONE);
						return;
					}

					if (hasCraftedPlanksPreviously(player, ItemTags.PLANKS)) {
						this.tutorial.setStep(TutorialSteps.NONE);
						return;
					}
				}
			}

			if (this.timeWaiting >= 1200 && this.toast == null) {
				this.toast = new TutorialToast(minecraft.font, TutorialToast.Icons.WOODEN_PLANKS, CRAFT_TITLE, CRAFT_DESCRIPTION, false);
				minecraft.gui.toastManager().addToast(this.toast);
			}
		}
	}

	@Override
	public void clear() {
		if (this.toast != null) {
			this.toast.hide();
			this.toast = null;
		}
	}

	@Override
	public void onGetItem(final ItemStack itemStack) {
		if (itemStack.is(ItemTags.PLANKS)) {
			this.tutorial.setStep(TutorialSteps.NONE);
		}
	}

	public static boolean hasCraftedPlanksPreviously(final LocalPlayer player, final TagKey<Item> tag) {
		for (Holder<Item> item : BuiltInRegistries.ITEM.getTagOrEmpty(tag)) {
			if (player.getStats().getValue(Stats.ITEM_CRAFTED.get((Item)item.value())) > 0) {
				return true;
			}
		}

		return false;
	}
}
