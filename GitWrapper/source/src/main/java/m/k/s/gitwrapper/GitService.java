package m.k.s.gitwrapper;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Component;

/**
 * This service provides interfaces to: <br/>
 * - clone source code from git.
 */
@Component
public class GitService {
	/** For logging. */
	private final static Logger LOG = Logger.getLogger(GitService.class);

	/** Account name. */
	private String username;

	/** Account password. */
	private String password;

	/**
	 * Create an instance of git service.
	 * 
	 * @param username
	 *            account name
	 * @param password
	 *            account password
	 */
	public GitService(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Clone
	 * 
	 * @param url
	 *            URL of repository
	 * @param cloneLocation
	 *            Location where contains source will be gotten
	 */
	public void cloneGit(String url, String cloneLocation) {
		boolean cloneSubmodules = true;

		UsernamePasswordCredentialsProvider userCredential = new UsernamePasswordCredentialsProvider(username,
				password);
		Git git = null;
		File fileCheckoutLocation = new File(cloneLocation);
		try {

			git = Git.cloneRepository().setDirectory(fileCheckoutLocation).setCredentialsProvider(userCredential)
					.setURI(url).setProgressMonitor(new TextProgressMonitor()).setCloneSubmodules(cloneSubmodules)
					.call();
		} catch (InvalidRemoteException ex) {
			LOG.error("Could not clone project '" + url + "'", ex);
		} catch (TransportException ex) {
			LOG.error("Could not clone project '" + url + "'", ex);
		} catch (GitAPIException ex) {
			LOG.error("Could not clone project '" + url + "'", ex);
		} catch (JGitInternalException ex) {
			LOG.error("Could not clone project '" + url + "'", ex);
		} finally {
			// Close git connection
			if (git != null) {
				git.getRepository().close();
			}
		}
	}

	/**
	 * pull
	 * 
	 * @param filePullLocation
	 *            file will be updated after pull
	 * @return
	 */
	public boolean pullGit(File filePullLocation) {
		UsernamePasswordCredentialsProvider userCredential = new UsernamePasswordCredentialsProvider(username,
				password);
		Git git = null;
		try {
			Repository localRepo = new FileRepository(filePullLocation);
			git = new Git(localRepo);
			PullCommand pullCmd = git.pull();
			pullCmd.setCredentialsProvider(userCredential);
			pullCmd.call();
			return true;
		} catch (Exception ex) {
			LOG.error("Could not Pull for project '" + filePullLocation.getPath() + "'");
			LOG.error(ex);
			return false;
		} finally {
			if (git != null) {
				git.getRepository().close();
			}
		}
	}
}
