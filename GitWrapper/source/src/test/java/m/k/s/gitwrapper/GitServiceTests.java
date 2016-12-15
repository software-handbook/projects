package m.k.s.gitwrapper;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import junit.framework.TestCase;

public class GitServiceTests extends TestCase {

	public void testGit() throws Exception {
		String url = "https://bitbucket.org/thachln/mks.git";
		String username = "";
		String password = "";
		String checkoutLocation = "D:/Temp/TestGit";

		String branch = "my-services-sample-test";

		UsernamePasswordCredentialsProvider userCredential = new UsernamePasswordCredentialsProvider(username,
				password);
		Git r = Git.cloneRepository().setDirectory(new File(checkoutLocation + "/clonned"))
				.setCredentialsProvider(userCredential).setURI(url).setProgressMonitor(new TextProgressMonitor())
				.setBranch(branch).call();
		System.out.println("Clonning @ " + checkoutLocation);
		r.getRepository().close();
	}

	/**
	 * Test clone 1 project.
	 * 
	 * @throws Exception
	 */
	public void testClone() throws Exception {
		String url = "https://bitbucket.org/thuanle/ttcnpm-151-32";
		String username = "";
		String password = "";
		String checkoutLocation = "D:/Temp/TestGit";

		GitService gitService = new GitService(username, password);

		gitService.cloneGit(url, checkoutLocation);

		System.out.println("Clonning @ " + checkoutLocation);
	}
}
