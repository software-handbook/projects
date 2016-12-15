package m.k.s.gitwrapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainProcess {

	public static void main(String[] args) throws Exception {
		final int n = 41; // Maximum index of project
		String username = "tuongpv";//put your account's username
		String password = "1234567890";//put your account's password

		GitService gitService = new GitService(username, password);

		// Format of URL: https://bitbucket.org/thuanle/ttcnpm-151-NN
		// Example: https://bitbucket.org/thuanle/ttcnpm-151-16
		final String prefixUrl = "https://bitbucket.org/thuanle/ttcnpm-151-";
		final String prefixCheckoutLocation = "D:/Temp/bitbucker-bk/ttcnpm-151-";//You can change other location but suffix always /ttcnpm-151-
		String[] urls = new String[n];
		String[] workingLocations = new String[n];

		// Build value for URLs and workingLocations
		String nn;
		for (int i = 0; i < n; i++) {
			if (i == 18) {
				continue;
			} // skip because the UserName not has authorize in project 19

			nn = String.format("%02d", i + 1);
			urls[i] = prefixUrl + nn;
			workingLocations[i] = prefixCheckoutLocation + nn;
			System.out.println("The app is working on " + workingLocations[i]);

			Path path = Paths.get(workingLocations[i]);
			if (Files.exists(path)) {
				File fileWorking = new File(workingLocations[i]+"/.git");
				gitService.pullGit(fileWorking);
			} else {
				gitService.cloneGit(urls[i], workingLocations[i]);
			}
		}

	}
}
