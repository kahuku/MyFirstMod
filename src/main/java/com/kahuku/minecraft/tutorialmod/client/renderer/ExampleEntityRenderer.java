package com.kahuku.minecraft.tutorialmod.client.renderer;

import com.kahuku.minecraft.tutorialmod.TutorialMod;
import com.kahuku.minecraft.tutorialmod.client.renderer.model.ExampleEntityModel;
import com.kahuku.minecraft.tutorialmod.common.entity.ExampleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ExampleEntityRenderer<Type extends ExampleEntity> extends MobRenderer<Type, ExampleEntityModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/entities/example_entity.png");

    public ExampleEntityRenderer(Context context) {
        super(context, new ExampleEntityModel<>(context.bakeLayer(ExampleEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}
