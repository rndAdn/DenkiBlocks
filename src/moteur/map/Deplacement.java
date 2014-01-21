package moteur.map;

/**
 * Created by renaud on 21/01/14.
 */
public class Deplacement {

	/**
	 * Regarde pour chaque Block si un autre Block fusionné ne peut pas se deplacer
	 * alors aucun des autres Blocks fusionnés ne peut se deplacer
	 */
	private static void checkCanMove(Map map,int x,int y){
		for(int i =0;i<y; i++){
			for(int j =0;j<x; j++){
				if (map.getCases()[i][j] instanceof Block){
					boolean allBlocksCanmove = true;
					for (Block b : map.getCases()[i][j].listeBlock){
						if (!b.canBeMove) allBlocksCanmove = false;
					}
					if (!allBlocksCanmove){//si un block ne peux pas bouger alors tout les block fusionnés ne pourront pas non plus
						for (Block b : map.getCases()[i][j].listeBlock){
							b.canBeMove = false;
						}
					}
				}
			}
		}
	}


	/**
	 * Regarde pour chaque block si la case du bas n'est pas un obstacle
	 */
	private static void checkMoveDown(Map map,int x,int y){
		for(int i =y-1;i>=0; i--){
			for(int j =0;j<x; j++){
				map.getCases()[i][j].canBeMove = !(map.getCases()[i][j] instanceof Block && map.getCases()[i + 1][j] instanceof Obstacle);
			}
		}
		checkCanMove(map,x,y);
	}

	/**
	 * Cette fonction deplace tout les blocks qui le peuvent vers le bas
	 */
	protected static void moveDown(Map map,int x,int y){
		checkMoveDown(map,x,y);
		for(int i =y-1;i>=0; i--){
			for(int j =0;j<x; j++){
				if (map.getCases()[i][j] instanceof Block && map.getCases()[i][j].canBeMove){
					map.getCases()[i+1][j] = map.getCases()[i][j];
					map.getCases()[i][j] = new Vide(map.color_themes[1]);
				}
			}
		}
	}

	/**
	 * Regarde pour chaque block si la case du haut n'est pas un obstacle
	 */
	private static void checkMoveUp(Map map,int x,int y){
		for(int i =1;i<y; i++){
			for(int j =0;j<x; j++){
				map.getCases()[i][j].canBeMove = !(map.getCases()[i][j] instanceof Block && map.getCases()[i - 1][j] instanceof Obstacle);
			}
		}
		checkCanMove(map,x,y);
	}

	/**
	 * Cette fonction deplace tout les blocks qui le peuvent vers le haut
	 */
	protected static void moveUp(Map map,int x,int y){
		checkMoveUp(map,x,y);
		for(int i =1;i<y; i++){
			for(int j =0;j<x; j++){
				if (map.getCases()[i][j] instanceof Block && map.getCases()[i][j].canBeMove){
					map.getCases()[i-1][j] = map.getCases()[i][j];
					map.getCases()[i][j] = new Vide(map.color_themes[1]);
				}
			}
		}
	}

	/**
	 * Regarde pour chaque block si la case du droite n'est pas un obstacle
	 */
	private static void checkMoveRight(Map map,int x,int y){
		for(int i =0;i<y; i++){
			for(int j =x-1;j>0; j--){
				map.getCases()[i][j].canBeMove = !(map.getCases()[i][j] instanceof Block && map.getCases()[i][j + 1] instanceof Obstacle);
			}
		}
		checkCanMove(map,x,y);
	}

	/**
	 * Cette fonction deplace tout les blocks qui le peuvent vers la droite
	 */
	protected static void moveRight(Map map,int x,int y){
		checkMoveRight(map,x,y);
		for(int i =1;i<y; i++){
			for(int j =x-1;j>0; j--){
				if (map.getCases()[i][j] instanceof Block && map.getCases()[i][j].canBeMove){
					map.getCases()[i][j+1] = map.getCases()[i][j];
					map.getCases()[i][j] = new Vide(map.color_themes[1]);
				}
			}
		}
	}

	/**
	 * Regarde pour chaque block si la case du gauche n'est pas un obstacle
	 */
	private static void checkMoveLeft(Map map,int x,int y){
		for(int i =0;i<y; i++){
			for(int j =1;j<x; j++){
				map.getCases()[i][j].canBeMove = !(map.getCases()[i][j] instanceof Block && map.getCases()[i][j - 1] instanceof Obstacle);
			}
		}
		checkCanMove(map,x,y);
	}

	/**
	 * Cette fonction deplace tout les blocks qui le peuvent vers la gauche
	 */
	protected static void moveLeft(Map map,int x,int y){
		checkMoveLeft(map,x,y);
		for(int i =y-1;i>0; i--){
			for(int j = 0;j<x-1; j++){
				if (map.getCases()[i][j] instanceof Block && map.getCases()[i][j].canBeMove){
					map.getCases()[i][j-1] = map.getCases()[i][j];
					System.out.println(map.color_themes);
					map.getCases()[i][j] = new Vide(map.color_themes[1]);
				}
			}
		}
	}
}
