package sit707_week5;

import org.junit.Assert;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class WeatherControllerTest {
	
	private static WeatherController wController;
    private static double[] hourlyTemperatures;
    private static int totalHours;
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("+++ setUpClass +++");

        wController = WeatherController.getInstance();
        totalHours = wController.getTotalHours();

        hourlyTemperatures = new double[totalHours];
        for (int i = 0; i < totalHours; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("+++ tearDownClass +++");

        if (wController != null) {
            wController.close();
        }
    }

	@Test
	public void testStudentIdentity() {
		String studentId = "225100695";
		Assert.assertNotNull("Student ID is null", studentId);
		Assert.assertFalse("Student ID is empty", studentId.trim().isEmpty());
	}

	@Test
	public void testStudentName() {
		String studentName = "Aneeta Palampalliparambil Boni";
		Assert.assertNotNull("Student name is null", studentName);
		Assert.assertFalse("Student name is empty", studentName.trim().isEmpty());
	}

	@Test
	public void testTemperatureMin() {
	    System.out.println("+++ testTemperatureMin +++");

	    double minTemperature = hourlyTemperatures[0];
	    for (int i = 1; i < totalHours; i++) {
	        if (minTemperature > hourlyTemperatures[i]) {
	            minTemperature = hourlyTemperatures[i];
	        }
	    }

	    Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.0001);
	}
	
	@Test
	public void testTemperatureMax() {
	    System.out.println("+++ testTemperatureMax +++");

	    double maxTemperature = hourlyTemperatures[0];
	    for (int i = 1; i < totalHours; i++) {
	        if (maxTemperature < hourlyTemperatures[i]) {
	            maxTemperature = hourlyTemperatures[i];
	        }
	    }

	    Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.0001);
	}

	@Test
	public void testTemperatureAverage() {
	    System.out.println("+++ testTemperatureAverage +++");

	    double totalTemperature = 0;
	    for (int i = 0; i < totalHours; i++) {
	        totalTemperature += hourlyTemperatures[i];
	    }

	    double averageTemperature = totalTemperature / totalHours;

	    Assert.assertEquals(averageTemperature, wController.getTemperatureAverageFromCache(), 0.0001);
	}
	
	@Test
	public void testTemperaturePersist() {
		/*
		 * Remove below comments ONLY for 5.3C task.
		 */
//		System.out.println("+++ testTemperaturePersist +++");
//		
//		// Initialise controller
//		WeatherController wController = WeatherController.getInstance();
//		
//		String persistTime = wController.persistTemperature(10, 19.5);
//		String now = new SimpleDateFormat("H:m:s").format(new Date());
//		System.out.println("Persist time: " + persistTime + ", now: " + now);
//		
//		Assert.assertTrue(persistTime.equals(now));
//		
//		wController.close();
	}
}
