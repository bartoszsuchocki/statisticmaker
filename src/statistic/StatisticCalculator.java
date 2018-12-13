package statistic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class StatisticCalculator {
	private String sourceFolderPath;
	private String searchedFilesExtension;
	private long linesCount;
	private long filesCount;
	private List<String> stringPaths;

	public StatisticCalculator() {
	}

	public StatisticCalculator(String sourceFolderPath, String searchedFilesExtension) {
		this.sourceFolderPath = sourceFolderPath;
		this.searchedFilesExtension = searchedFilesExtension;
	}

	public String getSourceFolderPath() {
		return sourceFolderPath;
	}

	public void setSourceFolderPath(String sourceFolderPath) {
		this.sourceFolderPath = sourceFolderPath;
	}

	public void setSearchedFilesExtension(String searchedFilesExtension) {
		this.searchedFilesExtension = searchedFilesExtension;
	}

	public String getSearchedFilesExtension() {
		return searchedFilesExtension;
	}

	public long getFilesCount() {
		return filesCount;
	}

	public long getLinesCount() {
		return linesCount;
	}

	public void calculateAll() throws IOException {
		Path searchRootPath = Paths.get(sourceFolderPath);
		int maxDepth = 50;

		if (stringPaths == null) {
			stringPaths = new ArrayList<>();
		}

		BiPredicate<Path, BasicFileAttributes> matcher = (path,
				fileAttributes) -> (path.toString().endsWith(searchedFilesExtension));

		Files.find(searchRootPath, maxDepth, matcher).collect(Collectors.toList()).forEach((path) -> {
			try {
				if (!(path.toFile().isDirectory())) {
					linesCount += Files.lines(path, Charset.forName("Cp1252")).count();
					stringPaths.add(path.toString());
				}
			} catch (IOException e) {
				// linesCount will simply not increment
			}
		});
		filesCount = stringPaths.size();
	}

	public void calculateLines(Path path) {
		Path searchRootPath = Paths.get(sourceFolderPath);
		File f = searchRootPath.toFile();

//		Files.lines(searchRootPath);
	}

	public void countLines() {

	}

}
