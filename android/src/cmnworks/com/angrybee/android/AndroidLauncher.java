/**
 * AngryBee
 * Was my first Android game app. Codes worth sharing.
 *
 * @copyright     Copyright (c) CMNWorks Christopher M. Natan
 * @author        Christopher M. Natan
 * @link          http://cmnworks.com
 * @version       1.0
 * @license       http://www.opensource.org/licenses/mit-license.php MIT License
 */
package cmnworks.com.angrybee.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import cmnworks.com.angrybee.AngryBee;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		AngryBee angryBee = new AngryBee();
		initialize(angryBee, config);
	}
}
