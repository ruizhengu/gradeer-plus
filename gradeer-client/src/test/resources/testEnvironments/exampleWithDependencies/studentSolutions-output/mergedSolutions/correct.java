/* Comment generated by Gradeer
Source files of correct: 
- /uk/ac/sheffield/Catalog.java: 
- /uk/ac/sheffield/Dashboard.java: 
*/


/* Comment generated by Gradeer
==============
Catalog.java
==============
*/

package uk.ac.sheffield;

import uk.ac.sheffield.codeprovided.AbstractCatalog;

public class Catalog extends AbstractCatalog
{
    public Catalog(String filename) {
        super(filename);
    }

    @Override
    public String parseLine(String line) throws IllegalArgumentException {
        return line.substring(0, line.indexOf(","));
    }
}

/* Comment generated by Gradeer
==============
Dashboard.java
==============
*/

package uk.ac.sheffield;

import uk.ac.sheffield.codeprovided.AbstractCatalog;
import uk.ac.sheffield.codeprovided.gui.DashboardFrame;

public class Dashboard {

	private final AbstractCatalog catalog;

	public static void main(String[] args) {
		if (args.length == 0) {
			args = new String[]{ "src/main/resources/epl-2223-min.csv" };
		}
		String file = args[0];

		Dashboard dashboard = new Dashboard(file);

		dashboard.startCLI();
		dashboard.startGUI();
	}

	public Dashboard(String file) {
		catalog = new Catalog(file);
	}

	public void startCLI() {
		System.out.println("Entries in the dataset:");
		System.out.println(catalog.getEntries());
	}

	public void startGUI() {
		DashboardFrame entryDashboard = new DashboardFrame();
		entryDashboard.setVisible(true);
	}
}

