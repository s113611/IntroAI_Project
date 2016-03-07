import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static Graph g;

	public static void main(String[] args) throws IOException {
		g = CreateGraph("src/RouteFindingCph.txt");
		for (Edge edg : g.edges) {
			System.out.println(
					"Start coord " + edg.getFrom().getX() + "," + edg.getFrom().getY() + " name : " + edg.getName());
		}

	}

	public static Graph CreateGraph(String path) throws IOException {
		Graph g = new Graph();
		ArrayList<Edge> edges = new ArrayList<Edge>();

		String[] lines = ImportText(path);
		if (lines.length == 0)
			return null;

		for (String line : lines) {
			String[] split = line.split("\\s+");
			Edge edg = new Edge();
			edg.setFrom(new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
			edg.setTo(new Node(Integer.parseInt(split[3]), Integer.parseInt(split[4])));
			edg.setName(split[2]);
			edg.setOneWay(true);
			edges.add(edg);

		}

		g.setEdges(edges);
		return g;
	}

	public static String[] ImportText(String fileName) throws IOException {
		String result;

		try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
			StringBuilder sb = new StringBuilder();
			String line = bf.readLine();

			while (line != null) {
				sb.append(line);
				if (!line.trim().equals(""))
					sb.append(System.lineSeparator());
				line = bf.readLine();
			}
			result = sb.toString();
		}
		return result.split(System.getProperty("line.separator"));
	}

}
