import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Comparator;

public class App {
    Scanner sc = new Scanner(System.in);
    boolean isEnd = false;
    int lastId = 1;
    List<WiseSaying> wiseList = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (!isEnd) {
            System.out.print("명령) ");
            String[] parts = sc.nextLine().split("\\?");
            String cmd = parts[0];

            switch (cmd) {
                case "종료":
                    isEnd = true;
                    break;
                case "등록":
                    actionRegister();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionDelete(extractId(parts[1]));
                    break;
                case "수정":
                    actionEdit(extractId(parts[1]));
                    break;
            }
        }
    }

    private void actionRegister() {
        System.out.print("명언 : ");
        String content = sc.nextLine();

        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying item = write(author, content);

        System.out.printf("%d번 명언이 등록되었습니다.\n", item.getId());
    }

    private WiseSaying write(String author, String content) {
        int id = lastId++;
        WiseSaying item = new WiseSaying(id, author, content);

        wiseList.add(item);

        return item;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        wiseList.stream()
                .sorted(Comparator.comparingInt(WiseSaying::getId).reversed())
                .forEach(e -> System.out.println(e.toListString()));
    }

    private int extractId(String cmd) {
        String[] parts = cmd.split("=");

        return Integer.parseInt(parts[1]);
    }

    private void actionDelete(int id) {
        boolean isDelete = wiseList.removeIf(e -> e.getId() == id);

        System.out.println(id + "번 명언" + (isDelete ? "이 삭제되었습니다." : "은 존재하지 않습니다."));
    }

    private WiseSaying findById(int id) {
        Optional<WiseSaying> opWise = wiseList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();

        return opWise.orElse(null);
    }

    private void actionEdit(int id) {
        WiseSaying item = findById(id);

        if (item == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + item.getContent());
        System.out.printf("명언 : ");
        String content = sc.nextLine();

        System.out.println("작가(기존) : " + item.getAuthor());
        System.out.printf("작가 : ");
        String author = sc.nextLine();

        edit(item, author, content);
    }

    private void edit(WiseSaying item, String author, String content) {
        item.setAuthor(author);
        item.setContent(content);
    }
}
