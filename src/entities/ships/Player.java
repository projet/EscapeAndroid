package entities.ships;

import org.jbox2d.common.Vec2;

import com.example.escapeandroid.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import effects.Effects;
import effects.Explosion;
import entities.CollisionGroup;
import entities.Entities;
import entities.Entity;
import entities.weapons.Bonus;
import entities.weapons.ShiboleetExtended;
import entities.weapons.Weapon;
import entities.weapons.WeaponItem;
import entities.weapons.WeaponItems;
import factories.WeaponFactory.WeaponType;
import game.Ressources;
import game.Variables;

/**
 * This class represents a player, which is the player of the game.
 * 
 *  * @author Quentin Bernard et Ludovic Feltz
 */

/* <This program is an Shoot Them up space game, called Escape-IR, made by IR students.>
 *  Copyright (C) <2012>  <BERNARD Quentin & FELTZ Ludovic>

 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

public class Player extends Ship {

	/**
	 * List of items of the player.
	 */
	private final WeaponItems weaponItems;

	/**
	 * Image used to render the invincibility.
	 */
	private Bitmap[] invincibleImages;

	/**
	 * Image used to render the looping.
	 */
	private Bitmap[] loopingImages;

	/**
	 * Image used to render when the player is being touched.
	 */
	private Bitmap[] touchedImages;

	/**
	 * Looping step used to render.
	 */
	private final int LOOPING_STEP = 40;

	/**
	 * Invincible step used to render.
	 */
	private final int INVINCIBLE_STEP = 50;

	/**
	 * Invincible repeat time used to render.
	 */
	private final int INVINCIBLE_REPEAT = 3;

	/**
	 * Touch step.
	 */
	private final int TOUCHED_STEP = 50;

	/**
	 * Touch repeat time.
	 */
	private final int TOUCHED_REPEAT = 3;

	/**
	 * Boolean for know if the player is invincible.
	 */
	private boolean invincible;

	/**
	 * Boolean for know if the player is touched.
	 */
	private boolean touched;

	/**
	 * Boolean for know if the player is making a looping.
	 */
	private boolean looping;

	private int step, repeat;//local variables uses for launch the creation during all the game

	/**
	 * The index of the CurrentFrame of the image.
	 */
	private int currentFrame;

	/**
	 * An enum to know the direction of the movement of a player.
	 *
	 */
	public enum Direction{
		NONE,
		LEFT,
		RIGHT;
	}

	/**
	 * The direction of his looping.
	 */
	private Direction loopDirection;


	/**
	 * Default constructor.
	 * Init a player into our world entities.
	 * @param entities - class which represents our world
	 */
	public Player(Entities entities){
		super(entities, EntityShape.Polygon, Ressources.getImage(R.drawable.joueur), Variables.SCREEN_WIDTH/2, Variables.SCREEN_HEIGHT/7, Variables.SHIP_LIFE);
		weaponItems = new WeaponItems();
		
		weaponItems.add(new WeaponItem(WeaponType.Missile, 10));
		weaponItems.add(new WeaponItem(WeaponType.Fireball, 25));
		weaponItems.add(new WeaponItem(WeaponType.ShiboleetExtended, 25));
		weaponItems.add(new WeaponItem(WeaponType.Shuriken, 30));
		
		invincible = true;
		touched = false;
		looping = false;

		step=0;
		currentFrame=0;

		/*
		 * Set images here
		 */
		invincibleImages = new Bitmap[5];
		invincibleImages[0] = Ressources.getImage(R.drawable.joueur_invincible1);
		invincibleImages[1] = Ressources.getImage(R.drawable.joueur_invincible2);
		invincibleImages[2] = Ressources.getImage(R.drawable.joueur_invincible3);
		invincibleImages[3] = Ressources.getImage(R.drawable.joueur_invincible4);
		invincibleImages[4] = Ressources.getImage(R.drawable.joueur_invincible5);
		
		
		
		
		
		
		
		
		
		
		loopingImages = new Bitmap[22];
		loopingImages[0] = Ressources.getImage(R.drawable.joueur_loop1);	
		loopingImages[1] = Ressources.getImage(R.drawable.joueur_loop2);
		loopingImages[2] = Ressources.getImage(R.drawable.joueur_loop3);
		loopingImages[3] = Ressources.getImage(R.drawable.joueur_loop4);
		loopingImages[4] = Ressources.getImage(R.drawable.joueur_loop5);
		loopingImages[5] = Ressources.getImage(R.drawable.joueur_loop6);
		loopingImages[6] = Ressources.getImage(R.drawable.joueur_loop7);
		loopingImages[7] = Ressources.getImage(R.drawable.joueur_loop8);
		loopingImages[8] = Ressources.getImage(R.drawable.joueur_loop9);
		loopingImages[9] = Ressources.getImage(R.drawable.joueur_loop10);
		loopingImages[10] = Ressources.getImage(R.drawable.joueur_loop11);
		loopingImages[11] = Ressources.getImage(R.drawable.joueur_loop12);
		loopingImages[12] = Ressources.getImage(R.drawable.joueur_loop13);
		loopingImages[13] = Ressources.getImage(R.drawable.joueur_loop14);
		loopingImages[14] = Ressources.getImage(R.drawable.joueur_loop15);
		loopingImages[15] = Ressources.getImage(R.drawable.joueur_loop16);
		loopingImages[16] = Ressources.getImage(R.drawable.joueur_loop17);
		loopingImages[17] = Ressources.getImage(R.drawable.joueur_loop18);
		loopingImages[18] = Ressources.getImage(R.drawable.joueur_loop19);
		loopingImages[19] = Ressources.getImage(R.drawable.joueur_loop20);
		loopingImages[20] = Ressources.getImage(R.drawable.joueur_loop21);
		loopingImages[21] = Ressources.getImage(R.drawable.joueur_loop22);
		
		touchedImages = new Bitmap[4];
		touchedImages[0] = Ressources.getImage(R.drawable.joueur_red1);
		touchedImages[1] = Ressources.getImage(R.drawable.joueur_red2);
		touchedImages[2] = Ressources.getImage(R.drawable.joueur_red3);
		touchedImages[3] = Ressources.getImage(R.drawable.joueur_red4);
		
		/*
		for(int i=0; i<invincibleImages.length; i++){
			invincibleImages[i] = Ressources.getImage("ships/playerShip/Joueur_invincible"+(i+1)+".png");	
		}
		for(int i=0; i<loopingImages.length; i++){
			loopingImages[i] = Ressources.getImage("ships/playerShip/Joueur_loop"+(i+1)+".png");	
		}
		for(int i=0; i<touchedImages.length; i++){
			touchedImages[i] = Ressources.getImage("ships/playerShip/Joueur_red"+(i+1)+".png");
		}
		*/

		getBody().setFixedRotation(true);
		setCollisionListener(true);
		setCollisionGroup(EntityType.Joueur);
		setDamping(Variables.SHIP_DAMPING);
	}

	/**
	 * Set the collision group when the player is collide.
	 * @param isListener - if false, the player only collide with bonus, else normal group collision used.
	 */
	private void setCollisionListener(boolean isListener){
		setCollisionGroup(EntityType.Joueur);
		if(!isListener){
			getBody().getFixtureList().getFilterData().maskBits = CollisionGroup.BONUS_COLLISION;
		}
	}

	/*
	 * Animation setters
	 */
	/**
	 * Set the looping of the player depending of the direction.
	 * @param direction - direction of the looping
	 */
	public void setLooping(Direction direction){
		step=0;
		invincible=false;
		touched=false;
		loopDirection = direction;

		switch(direction){
		case NONE:
			looping=false;
			currentFrame=0;
			setCollisionListener(true);
			break;
		case LEFT:
			looping=true;
			currentFrame = loopingImages.length-1;
			setCollisionListener(false);
			break;
		case RIGHT:
			looping=true;
			currentFrame = 0;
			setCollisionListener(false);
			break;
		} 
	}

	/**
	 * Set the player as invincible depending of the value
	 * @param invincible - if true, the player will be invincible, else no changes.
	 */
	private void setInvicible(boolean invincible){
		this.invincible=invincible;
		touched=false;
		looping=false;
		currentFrame=0;
		step=0;

		setCollisionListener(!invincible);
	}

	/**
	 * Set the player as touched depending of the value. If he has been touched, he will be invincible during a little time.
	 * @param touched - if true, the player will be touch, else no changes.
	 */
	private void setTouched(boolean touched){
		this.touched=touched;
		looping=false;
		invincible=false;
		currentFrame=0;
		step=0;
		setCollisionListener(!touched);
	}



	@Override
	public void compute() {
		Vec2 pos = getPositionNormalized();
		Vec2 vel = getVelocity();

		/*
		 * Player limits
		 */
		if( (vel.x<0 && pos.x<0) ||
				(vel.x>0 && pos.x>Variables.SCREEN_WIDTH - getImage().getWidth()))
			vel.x=0;

		if( (vel.y<0 && pos.y<getImage().getHeight()) || (vel.y>0 && pos.y>Variables.SCREEN_HEIGHT))
			vel.y=0;

		setVelocity(vel.x, vel.y);
	}


	/**
	 * All renders, depending of the characteristics of the player.
	 * The player can be invincible, touched and can do a loop.
	 * @see entities.ships.Ship#getImage()
	 * @return the Image associated with the current step of the animation
	 */
	@Override
	public Bitmap getImage() {		
		step++;
		if(invincible){
			return invincibleRender();
		}		
		else if(touched){
			return touchedRender();
		}
		else if(looping){
			return loopRender();
		}		
		else{
			return super.getImage();
		}
	}

	/**
	 * Do the render of the invincibility of the player
	 * @return the Image associated with the current step of the invincibility
	 */
	private Bitmap invincibleRender(){
		Bitmap image = invincibleImages[currentFrame];
		if(step>INVINCIBLE_STEP){
			step=0;		
			currentFrame++;
			if(currentFrame>=invincibleImages.length){
				currentFrame=0;
				repeat++;
			}
			if(repeat>INVINCIBLE_REPEAT){
				repeat=0;
				setInvicible(false);
			}
		}
		return image;		
	}

	/**
	 * Do the render when the player is touched
	 * @return the Image associated with the current step of the animation
	 */
	private Bitmap touchedRender(){
		Bitmap image = touchedImages[currentFrame];
		if(step>TOUCHED_STEP){
			step=0;			
			currentFrame++;
			if(currentFrame>=touchedImages.length){
				currentFrame=0;
				repeat++;
			}
			if(repeat>TOUCHED_REPEAT){
				repeat=0;
				setTouched(false);
			}
		}
		return image;		
	}

	/**
	 * Do the render of the loop of the player
	 * @return the Image associated with the current step of the loop
	 */
	private Bitmap loopRender(){	
		Bitmap image = loopingImages[currentFrame];
		if(step>LOOPING_STEP){
			step=0;			
			switch(loopDirection){
			case LEFT:
				currentFrame--;
				break;
			case RIGHT:
				currentFrame++;
				break;
			default:
				break;
			}

			if(currentFrame<0 || currentFrame>=loopingImages.length){
				setLooping(Direction.NONE);
			}
		}
		return image;
	}



	@Override
	public EntityType getEntityType() {
		return EntityType.Joueur;
	}

	@Override
	public int getDamage() {
		return 10;
	}

	/**
	 * When collide with the boss, the enemy and the weapon : decrease the life of player, and set it as touched.
	 * If collide with Bonus, he catch it.
	 */
	@Override
	public void collision(Entity entity, EntityType type) {
		switch(type){
		case Boss:
		case Enemy:
		case WeaponEnnemy:
			setTouched(true);
			setLife(getLife()-entity.getDamage());
			if(getLife()<10){
				Vec2 pos = getScreenPostion();
				Effects.addEffect(new Explosion((int)pos.x, (int)pos.y));
				getEntities().removeEntitie(this);
			}
			break;
		case Bonus :
			Bonus bonus = (Bonus) (entity);
			weaponItems.addWeaponItem(bonus.getWeaponType(), bonus.getQuantity());
			getEntities().removeEntitie(entity);
			break;
		default:
			break;
		}

	}
	
	/**
	 * Return the weaponItems of the player, which is his list of WeaponItem.
	 * @see WeaponItems
	 * @see WeaponItem
	 * @return the weaponItems of the player
	 */
	public WeaponItems getWeapons(){
		return weaponItems;
	}

	/**
	 * Load the current WeaponItem of the player as a weapon.
	 */
	public void loadWeapon() {
		if(!weaponItems.isEmpty())
			loadWeapon(weaponItems.getCurrentWeaponItem().getWeaponType(), true);
	}

	public void weaponFollowPlayer(Weapon weapon){
		Vec2 posPlayer = getScreenPostion();
		
		float x = posPlayer.x + getImage().getWidth()/4;
		float y = posPlayer.y - weapon.getImage().getHeight();
		weapon.setPosition(x, y);
		
	}
	
	@Override
	public void render(Canvas canvas) {
		super.render(canvas);
		if(weapon != null)
			weaponFollowPlayer(weapon);
		if(weapon instanceof ShiboleetExtended){
			for(int i=0; i< ((ShiboleetExtended) weapon).child.length; i++)
				weaponFollowPlayer(((ShiboleetExtended) weapon).child[i]);
		}
	}

	
	
}
