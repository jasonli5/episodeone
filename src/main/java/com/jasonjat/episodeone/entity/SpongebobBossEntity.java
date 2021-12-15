package com.jasonjat.episodeone.entity;

import com.jasonjat.episodeone.util.AnimationQueue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SpongebobBossEntity extends HostileEntity implements IAnimatable, IAnimationTickable {

    private final AnimationFactory factory = new AnimationFactory(this);
    private final AnimationQueue queue = new AnimationQueue(this);
    private final ServerBossBar bossBar;

    @Override
    protected void initGoals() {
        int maxSpeed = 2;
        goalSelector.add(1, new MeleeAttackGoal(this, maxSpeed, true));
        goalSelector.add(5, new LookAroundGoal(this));

        targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public SpongebobBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.YELLOW, BossBar.Style.PROGRESS)).setDarkenSky(true);
        this.stepHeight = 4F;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(getHealth()/getMaxHealth());
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 5, event -> queue.tick(event, this::predicate)));
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spongebob_boss.sprint", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spongebob_boss.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
        ServerWorld world = (ServerWorld) target.getWorld();
        world.spawnParticles(ParticleTypes.FLAME, target.getX(), target.getY(), target.getZ(), 25, 0,0,0, 1);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return true;
    }

    @Override
    public int tickTimer() {
        return age;
    }
}
