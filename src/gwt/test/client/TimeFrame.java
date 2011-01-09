/*This file is part of GWTScalaCharting

GWTScalaCharting is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.

GWTScalaCharting is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>. */

package gwt.test.client;

public enum TimeFrame {
	All("All"),
	Years5("5Years"),
	Years2("2Years"),
	Year1("1Year"),
	Months6("6Months"),
	Months2("2Months"),
	Month1("1Month");
	
	private final String timeFrame;
	
	TimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	
	public String getTimeFrame() {
		return timeFrame;
	} 
	
	@Override
	public String toString() {
		return timeFrame;
	}
}
