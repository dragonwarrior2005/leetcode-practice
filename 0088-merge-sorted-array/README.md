# 88. Merge Sorted Array

**Difficulty:** Easy · **Topics:** Array, Two Pointers, Sorting

## Problem

You get two sorted arrays, `nums1` (length `m + n`, with `m` real values followed by `n` zeros) and `nums2` (length `n`). Merge `nums2` into `nums1` **in place** so that `nums1` ends up fully sorted.

## Approach: three pointers, filling from the back

The obvious idea is to merge from the front like in merge sort. The problem is that writing to the front of `nums1` would overwrite values we haven't looked at yet.

The back of `nums1` is empty (just placeholder zeros), so we fill it from right to left instead:

- `i` points at the last real element of `nums1` (index `m - 1`)
- `j` points at the last element of `nums2` (index `n - 1`)
- `k` points at the last slot of `nums1` (index `m + n - 1`)

At each step, compare `nums1[i]` and `nums2[j]`. Put the **larger** one at `nums1[k]` and move that pointer and `k` one step left. Repeat until `nums2` runs out (`j < 0`).

### Why this never overwrites unread data
`k` always equals `i + j + 1`. While `j >= 0`, `k > i`, so the slot we write to is never one we still need to read.

### Why we only loop while `j >= 0`
- If `nums2` runs out first, whatever is left in `nums1[0..i]` is already sorted and already in the right place.
- If `nums1` runs out first (`i < 0`), the `i >= 0` check fails, so we just copy the rest of `nums2` over.

## Walkthrough (Example 1)

`nums1 = [1,2,3,0,0,0]`, `m = 3`, `nums2 = [2,5,6]`, `n = 3`

| Step | compare | write | nums1 |
|---|---|---|---|
| 1 | 3 vs **6** | nums1[5] = 6 | [1,2,3,0,0,6] |
| 2 | 3 vs **5** | nums1[4] = 5 | [1,2,3,0,5,6] |
| 3 | **3** vs 2 | nums1[3] = 3 | [1,2,3,3,5,6] |
| 4 | 2 vs **2** (tie → nums2) | nums1[2] = 2 | [1,2,2,3,5,6] |
| done | j < 0 | — | **[1,2,2,3,5,6]** |

## Complexity

- **Time:** O(m + n). Every element is placed exactly once.
- **Space:** O(1). The merge happens in place.

## Alternative (simpler, slower)
Copy `nums2` into the tail of `nums1`, then call `Arrays.sort(nums1)`. That takes O((m+n) log(m+n)) time and ignores the fact that both inputs are already sorted.
