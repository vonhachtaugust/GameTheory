public void reproductionStep(Grid origGrid) {
	int row = origGrid.getRow();
	int col = origGrid.getCol();
	
	Object[][] origLattice = origGrid.getPlayers();
	Object[][] newLattice = origLattice.clone();
	
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			
			// Current player
			Player p = (Player) origLattice[i][j];
			double myScore = p.getScore();
			
			// Best strategy so far
			int bestStrat = p.getState();
			
			// Place the neighbours, Player objects, in a list
			ArrayList<Player> neighbours = new arrayList<Player>();
			
			// Neighbour above
			int up = origGrid.getIndAbove(i);
			Player upPlayer = (Player) origLattice[up][j];
			neighbours.add(upPlayer);
			
			// Neighbour below
			int down = origGrid.getIndBelow(i);
			Player downPlayer = (Player) origLattice[down][j];
			neighbours.add(downPlayer);
			
			// Neighbour to the right
			int right = origGrid.getIndRightOf(j);
			Player rightPlayer = (Player) origLattice[i][right];
			neighbours.add(rightPlayer);
			
			// Neighbour to the left
			int left = origGrid.getIndLeftOf(j);
			Player leftPlayer = (Player) origLattice[i][left];
			neighbours.add(leftPlayer);
			
			// Check for highest score and update bestStrat
			for (var n = 0; n < neighbours.size(); n++) {
				Player thisPlayer = neighbours.get(n);
				if (thisPlayer.getScore() > myScore) {
					bestStrat = thisPlayer.getState();
				}
			}
			
			Player newP = (Player) newLattice[i][j];
			newP.setState(bestStrat);
		}
	}
	
	origGrid.updateLattice(newLattice);
}