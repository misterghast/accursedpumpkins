package org.hypbase.ghast.accursed;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.ToolType;
public class CustomPumpkin extends Block {
	public static final IntegerProperty DESIGN = IntegerProperty.create("design", 0, 25);
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	//public static final DirectionProperty DIRECTION = DirectionProperty.create("direction", 
		//Direction.values());
	public CustomPumpkin(/*Properties properties*/) {
		super(AbstractBlock.Properties.of(Material.BAMBOO));
		//this.setRegistryName("custompumpkin");
		//this.registerDefaultState(this.stateDefinition.any().setValue(DESIGN, 0));
		
	}
	
	@Override
	public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DESIGN);
		builder.add(LIT);
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		Block b = state.getBlock();
		Collection<Property<?>> p = state.getProperties();
		System.out.println("Event is being called for right click on custom pumpkin block.");
		if(player.isHolding(Items.TORCH)) {
			for(Property<?> prop : p) {
				
				if(prop.getName().equals("lit")) {
					BooleanProperty bp = (BooleanProperty) prop;
					boolean isLit = state.getValue(bp);
					if(isLit) {
						isLit = false;
					} else {
						isLit = true;
					}
					
					worldIn.setBlock(pos, state.setValue(bp, isLit), 0);
					return ActionResultType.SUCCESS;
				}
			}
		}
		
		if(player.isHolding(Items.AIR)) {
			int designs = ConfigUtils.designs;
			for(Property<?> prop : p) {
				
				if(prop.getName().equals("design")) {
					IntegerProperty ip = (IntegerProperty) prop;
					int boundedInt = (int) state.getValue(ip) - 1;
					if(boundedInt < 0) {
						if(ConfigUtils.designs == 0) {
							boundedInt = designs;
						} else {
							boundedInt = designs;
						}						
					}
					else if(boundedInt > designs) {
						boundedInt = 0;
					}
					worldIn.setBlock(pos, state.setValue(ip, boundedInt), 0);
					System.out.println(designs);
					return ActionResultType.SUCCESS;
						
				}
			}
		}
		return ActionResultType.FAIL;
	}
	
}
