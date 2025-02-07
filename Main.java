package open.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.lang.Exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.lang.reflect.Method;

public class Main {

Context context;
String tag = "mytag";
String TAG = "mytag";
public static final String LOG_TAG = "mytag";

public static Context getSystemContext() {
	try {
		Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
		Method method = activityThreadClass.getMethod("systemMain");
		Object activityThread = method.invoke(null);

		Method getSystemContextMethod = activityThreadClass.getMethod("getSystemContext");
		return (Context) getSystemContextMethod.invoke(activityThread);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

void log(String s) {
	System.out.println(s);
}

void sleep(int s){
	try {
		Thread.sleep(s);
	} catch (Exception e) {}
}

String create_folder(Context context) {
	File[] directory = new File[0];
	directory = context.getExternalMediaDirs();
	for(int i = 0;i<directory.length;i++){
		if(directory[i].getName().contains(context.getPackageName())){
			return directory[i].getAbsolutePath();
		}
	}
	return null;
}

void list(Context context){
	// In your Activity or Context
	String[] files = context.fileList();
	// You can iterate through the file names
	for (String filename : files) {
		log("files/" + filename);
	}
}


void list2(Context context){
	// In your Activity or Context
	// File privateRootDir = context.getFilesDir();
	// File privateRootDir = context.getDataDir();
	File privateRootDir = context.getDataDir(); 
	// File privateRootDir = new File(context.getDataDir(), "shared_prefs");

	// File imagesDir = new File(privateRootDir, "images");
	// File[] imageFiles = imagesDir.listFiles();
	File[] imageFiles = privateRootDir.listFiles();

	if (imageFiles != null) {
		for (File file : imageFiles) {
			// log("File absolute path: " + file.getAbsolutePath());
			log((file.isDirectory() ? "Dir" : "File") + ": " + file.getPath());
			// log("Is directory: " + file.isDirectory());
		}
	}
}

void list3(){
	List<File> filesList = getRecursiveFileList(context.getDataDir());
	for (File file : filesList) {
		log(file.toString());
	}
}

List<File> getRecursiveFileList(File directory) {
	// Example usage in an Android activity or service:
	// File rootDir = new File(android.os.Environment.getExternalStorageDirectory().toString() + "/YourAppFolder");
	// List<File> allFilesInFolder = getRecursiveFileList(rootDir);
	// File directory = new File(context.getDataDir());
	List<File> filesList = new ArrayList<>();
	File[] files = directory.listFiles();

	if (files != null) { // Check for null to handle permission issues or non-existent directories
		for (File file : files) {
			// if (file.isFile()) {
			filesList.add(file);
			// } else if (file.isDirectory()) {
			if (file.isDirectory()) {
				filesList.addAll(getRecursiveFileList(file)); // Recursive call
			}
			}
		}
		return filesList;
	}


void listFilesRecursive(String directoryPath) {
	List<Path> result;
	try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {
		result = walk
			// .filter(Files::isRegularFile); // Filters for regular files, excluding directories
			.collect(Collectors.toList());
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
	// return result;
	// System.out.println("--- Iterating using Enhanced For Loop (for-each) ---");
	for (Path path : result) {
		log(path.toString());
	}
}


void writeFileOnInternalStorage(String sFileName, String sBody) {
	try {
		// Use openFileOutput to create or open the file in the app's internal "files" directory
		FileOutputStream fileout = context.openFileOutput(sFileName, Context.MODE_PRIVATE);

		// Use OutputStreamWriter for writing character data
		OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
		outputWriter.write(sBody);
		outputWriter.close(); // Close the writer to save the data and release the file

		// Display a message
		// Toast.makeText(getBaseContext(), "File saved successfully in internal storage!", Toast.LENGTH_SHORT).show();
		// log("file saved");

	} catch (IOException e) {
		// e.printStackTrace();
		log(e.toString());
		// Toast.makeText(getBaseContext(), "Error saving file", Toast.LENGTH_SHORT).show();
	}
}

void readFileOnInternalStorage(String sFileName) {
	try {
	File file = new File(context.getDataDir(), sFileName);
		FileInputStream in = new FileInputStream(file);
		// FileInputStream in = openFileInput(sFileName);
		InputStreamReader inputStreamReader = new InputStreamReader(in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line);
		}
		inputStreamReader.close();
		log(sFileName+"="+sb.toString());
	} catch (Exception e) {
		log(e.toString());
	}
}


String readFromCacheFile(Context context, String filename) {
	// 1. Get the cache directory and file reference
	// File file = new File(context.getCacheDir(), filename);
	File file = new File(context.getCacheDir(), filename);

	// Check if the file exists before attempting to read
	if (!file.exists()) {
		Log.e(TAG, "File not found in cache: " + filename);
		return null;
	}

	StringBuilder content = new StringBuilder();
	try {
		// 2. Set up input streams to read the file
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		String line;
		// 3. Read line by line
		while ((line = br.readLine()) != null) {
			content.append(line);
			// Optional: append a new line character if needed for formatting
			// content.append('\n');
		}

		// Close all the streams
		br.close();
		isr.close();
		fis.close();

	} catch (IOException e) {
		Log.e(TAG, "Error reading from cache file", e);
		return null;
	}

	return content.toString();
}


void savePref(){
	SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
	SharedPreferences.Editor editor = sharedPref.edit();
	editor.putString("key_string", "Hello World");
	editor.putInt("key_int", 123);
	editor.putBoolean("key_boolean", true);

	// To save the changes
	editor.apply(); // Asynchronous save

	editor.commit(); // Synchronous save (returns boolean success)
}

void loadPref(){
	// SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
	// SharedPreferences sharedPref = getPreferences(Context.MODE_MULTI_PROCESS);
	// SharedPreferences sharedPref = context.getActivity().getPreferences(Context.MODE_PRIVATE);
	// SharedPreferences sharedPref = context.getSharedPreferences("MainActivity.xml", Context.MODE_MULTI_PROCESSw);
	SharedPreferences sharedPref = context.getSharedPreferences("config", Context.MODE_MULTI_PROCESS);
	String userName = sharedPref.getString("key_string", "Default Name");
	int userAge = sharedPref.getInt("idle_timeout", 0);
	// boolean isLoggedIn = sharedPref.getBoolean("is_logged_in", false);
	log("key_string=" + userName);
	log("idle_timeout=" + userAge);
}



public void copyFileUsingStreams(File source, File dest) 
	// throws IOException 
{
	try {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	} catch (Exception e) {
		log(e.toString());
	}
}

void copy(String from, String to){
	if (to == "") to = from;
	File a = new File(android.os.Environment.getExternalStorageDirectory().toString() + "/"+from);
	File b = new File(getDataDir(),  "/shared_prefs/"+to);
	copyFileUsingStreams(a, b);
}

void context(){
	// Intent intent = new Intent(InstrumentationRegistry.getContext(), TestComponentsService.class);
	Workarounds.apply(true, true, true);
	context = FakeContext.get();
	log(c.getPackageName());
	context.getSystemService(Context.BATTERY_SERVICE);
}

public Main(){
try {
	log("begin");
	// context = this;
	// context = InstrumentationRegistry.getContext();
	// context = new Context();
	context();

	// writeFileOnInternalStorage("a", "1");
	// readFileOnInternalStorage("a");
	// readFileOnInternalStorage("files/a");
	// save();
	// list(this);
	// list2(this);
	// log("recurse 1");
	// list3();
	// log("recurse 2");
	// listFilesRecursive(".");
	
	// copy("MainActivity.xml");
	// copy("config.xml", "");
	loadPref();
	// copy("MainActivity2.xml");
	// sleep(500);
	// loadPref();
	// readFileOnInternalStorage("shared_prefs/MainActivity.xml");
} catch (Exception e) {
	log(e.toString());
}

}

public static void main(String[] args) {
	new Main();
}

}//class
