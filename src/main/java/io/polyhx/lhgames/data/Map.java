package io.polyhx.lhgames.data;

public class Map {
    public static final int DIMENSION = 16;

    private static Tile[][] fTiles;

    public Map(Tile[][] tiles) throws Exception {
        if(tiles.length != DIMENSION) {
            throw new Exception("Invalid Y size for map: " + tiles.length);
        }

        if(tiles[0].length != DIMENSION) {
            throw new Exception("Invalid Y size for map: " + tiles.length);
        }

        fTiles = tiles;
    }

    public Point getHeadPosition(Team team) {
        for(Tile[] row : fTiles) {
            for(Tile tile : row) {
                if(tile.getHeadOwner() == team) {
                    return tile.getPosition();
                }
            }
        }

        return null;
    }

    public int getTailLength(Team team) {
        int count = 0;
        for(Tile[] row : fTiles) {
            for(Tile tile : row) {
                if(tile.getTailOwner() == team) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getBodySize(Team team) {
        int count = 0;
        for(Tile[] row : fTiles) {
            for(Tile tile : row) {
                if(tile.getTeamOwner() == team) {
                    count++;
                }
            }
        }

        return count;
    }

    public Tile[][] getTiles() {
        return fTiles;
    }

    public static Map fromStrings(String[] strings) throws Exception {
        if(strings.length != DIMENSION * DIMENSION) {
            throw new Exception("The received map dimension isn't valid");
        }

        Tile[][] tiles = new Tile[DIMENSION][DIMENSION];
        for(int j = 0; j < DIMENSION; j++) {
            for(int i = 0; i < DIMENSION; i++) {
                String string = strings[j * DIMENSION + i];
                Point position = new Point(i, j);
                tiles[j][i] = Tile.fromString(position, string);
            }
        }

        return new Map(tiles);
    }

    @Override
    public String toString() {
        String string = "";
        for(Tile[] row : fTiles) {
            for(Tile tile : row) {
                string += String.format("%s,", tile.toString());
            }
            string += "\n";
        }

        return string;
    }
}