class FenwickTree {

    int[] tree;
    int n;

    FenwickTree(int n) {
        this.n = n;
        tree = new int[n + 1];
    }

    // Update operation
    void update(int index, int value) {
        while (index <= n) {
            tree[index] += value;
            index += index & (-index);
        }
    }

    // Prefix sum query
    int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }
        return sum;
    }

    public static void main(String[] args) {

        int inventory[] = {10, 20, 30, 40, 50};
        int n = inventory.length;

        FenwickTree ft = new FenwickTree(n);

        // Build Fenwick Tree
        for (int i = 0; i < n; i++) {
            ft.update(i + 1, inventory[i]);
        }

        System.out.println("Initial Inventory:");
        for (int x : inventory) {
            System.out.print(x + " ");
        }

        // Update operation
        System.out.println("\n\nUpdating index 3 by +15");

        inventory[2] += 15;
        ft.update(3, 15);

        System.out.println("\nUpdated Inventory:");
        for (int x : inventory) {
            System.out.print(x + " ");
        }

        // Print Fenwick Tree Array
        System.out.println("\n\nFenwick Tree Array:");
        System.out.print("[");
        for (int i = 1; i <= n; i++) {
            System.out.print(ft.tree[i]);
            if (i < n) System.out.print(", ");
        }
        System.out.println("]");

        // Total inventory
        System.out.println("\nTotal inventory: " + ft.query(5));
    }
}