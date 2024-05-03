package io.github.pokellelo.progressivellelo.loot;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

//import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

import net.neoforged.neoforge.common.loot.LootModifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.MapCodec;

public class AddItemModifier extends LootModifier {
    // EJEMPLOs para probar
    // https://github.com/neoforged/NeoForge/blob/1.20.x/tests/src/main/java/net/neoforged/neoforge/debug/loot/GlobalLootModifiersTest.java

    public static final Supplier<MapCodec<AddItemModifier>> CODEC = Suppliers
            .memoize(() -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst).and(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(m -> m.item))
                    .apply(inst, AddItemModifier::new)));

    private final Item item;

    public AddItemModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot,

            LootContext context) {

        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        generatedLoot.add(new ItemStack(this.item));

        return generatedLoot;
    }

    @Override
    public MapCodec<AddItemModifier> codec() {
        // Esto en los tutos regresaba IGlobalLootModifier
        return CODEC.get();
    }

}
