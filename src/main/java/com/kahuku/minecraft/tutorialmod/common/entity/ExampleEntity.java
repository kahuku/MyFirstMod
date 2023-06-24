package com.kahuku.minecraft.tutorialmod.common.entity;

import com.kahuku.minecraft.tutorialmod.core.init.EntityInit;
import com.kahuku.minecraft.tutorialmod.core.init.ItemInit;
import com.kahuku.minecraft.tutorialmod.core.init.SoundInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class ExampleEntity extends Animal {

    public ExampleEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        // AI to determine what the mob should do
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(ItemInit.EXAMPLE_ITEM.get()), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.24D);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return EntityInit.EXAMPLE_ENTITY.get().create(level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.EXAMPLE_ENTITY_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundInit.EXAMPLE_ENTITY_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.EXAMPLE_ENTITY_DEATH.get();
    }
}
