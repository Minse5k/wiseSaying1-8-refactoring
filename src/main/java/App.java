import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    boolean isEnd = false;
    int lastId = 1;
    WiseSaying[] wiseArr = new WiseSaying[100];

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

        wiseArr[id - 1] = item;

        return item;
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = lastId - 2; i >= 0; i--) {
            if (wiseArr[i] == null) continue;

            System.out.println(wiseArr[i].toListString());
        }
    }

    private int extractId(String cmd) {
        String[] parts = cmd.split("=");

        return Integer.parseInt(parts[1]);
    }

    private void actionDelete(int id) {
        int idx = id - 1;

        if (wiseArr[idx] == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        delete(idx);
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    private void delete(int idx) {
        wiseArr[idx] = null;
    }

    private void actionEdit(int id) {
        int idx = id - 1;

        if (wiseArr[idx] == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying item = wiseArr[idx];

        System.out.println("명언(기존) : " + item.getContent());
        System.out.printf("명언 : ");
        String content = sc.nextLine();

        System.out.println("작자(기존) : " + item.getAuthor());
        System.out.printf("작자 : ");
        String author = sc.nextLine();

        edit(idx, id, author, content);
    }

    private void edit(int idx, int id, String author, String content) {
        wiseArr[idx] = new WiseSaying(id, author, content);
    }
}
