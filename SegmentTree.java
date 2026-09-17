class SegmentTree {

    int[] seg;
    int[] lazy;

    public SegmentTree(int[] arr) {
        int n = arr.length;
        seg = new int[4 * n];
        lazy = new int[4 * n];

        build(1, 0, n - 1, arr);
    }

    // --------------------------------------------------
    // BUILD
    // --------------------------------------------------

    public void build(int idx, int low, int high, int[] arr) {

        if (low == high) {
            seg[idx] = arr[low];
            return;
        }

        int mid = (low + high) / 2;

        build(2 * idx, low, mid, arr);
        build(2 * idx + 1, mid + 1, high, arr);

        seg[idx] = seg[2 * idx] + seg[2 * idx + 1];
    }

    // --------------------------------------------------
    // PUSH LAZY VALUE DOWN
    // --------------------------------------------------

    private void push(int idx, int low, int high) {

        // Nothing to propagate
        if (lazy[idx] == 0) {
            return;
        }

        // Apply lazy value to children
        if (low != high) {

            int mid = (low + high) / 2;

            // Left child covers [low, mid]
            seg[2 * idx] += (mid - low + 1) * lazy[idx];
            lazy[2 * idx] += lazy[idx];

            // Right child covers [mid+1, high]
            seg[2 * idx + 1] += (high - mid) * lazy[idx];
            lazy[2 * idx + 1] += lazy[idx];
        }

        // Current node's lazy value has been propagated
        lazy[idx] = 0;
    }

    // --------------------------------------------------
    // RANGE QUERY
    // --------------------------------------------------

    public int query(int idx, int low, int high, int l, int r) {

        // No overlap
        if (r < low || high < l) {
            return 0;
        }

        // Complete overlap
        if (l <= low && high <= r) {
            return seg[idx];
        }

        // Before going to children,
        // propagate pending updates
        push(idx, low, high);

        int mid = (low + high) / 2;

        int leftSum = query(2 * idx, low, mid, l, r);

        int rightSum = query(2 * idx + 1, mid + 1, high, l, r);

        return leftSum + rightSum;
    }

    // --------------------------------------------------
    // RANGE UPDATE
    // Add val to every element in [l, r]
    // --------------------------------------------------

    public void rangeUpdate(int idx, int low, int high, int l, int r, int val) {

        // No overlap
        if (r < low || high < l) {
            return;
        }

        // Complete overlap
        if (l <= low && high <= r) {

            // Number of elements in this segment
            int length = high - low + 1;

            // Since every element increases by val,
            // sum increases by length * val
            seg[idx] += length * val;

            // Store update for children
            lazy[idx] += val;

            return;
        }

        // Partial overlap
        push(idx, low, high);

        int mid = (low + high) / 2;

        rangeUpdate(2 * idx, low, mid, l, r, val);

        rangeUpdate(2 * idx + 1, mid + 1, high, l, r, val);

        // Recalculate current node
        seg[idx] = seg[2 * idx] + seg[2 * idx + 1];
    }

    // --------------------------------------------------
    // POINT UPDATE
    // Set arr[i] = val
    // --------------------------------------------------

    public void update(int idx, int low, int high, int i, int val) {

        // Leaf node
        if (low == high) {
            seg[idx] = val;
            lazy[idx] = 0;
            return;
        }

        // Make sure pending range updates
        // are applied before going down
        push(idx, low, high);

        int mid = (low + high) / 2;

        if (i <= mid) {
            update(2 * idx, low, mid, i, val);
        } else {
            update(2 * idx + 1, mid + 1, high, i, val);
        }

        // Recalculate current node
        seg[idx] = seg[2 * idx] + seg[2 * idx + 1];
    }
}