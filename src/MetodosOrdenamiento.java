import java.util.*;

public class MetodosOrdenamiento {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad de elementos: ");
        int n = sc.nextInt();

        int[] original = new int[n];

        System.out.println("Ingrese los numeros:");

        for (int i = 0; i < n; i++) {
            original[i] = sc.nextInt();
        }

        char continuar;

        do {

            // COPIA DEL ARREGLO ORIGINAL
            int[] arreglo = new int[original.length];
            for (int i = 0; i < original.length; i++) {
                arreglo[i] = original[i];
            }

            System.out.println("\nSeleccione metodo de ordenamiento:");
            System.out.println("1. Selection Sort");
            System.out.println("2. Bubble Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Heap Sort");
            System.out.println("7. Counting Sort");
            System.out.println("8. Radix Sort");
            System.out.println("9. Bucket Sort");

            int op = sc.nextInt();

            switch (op) {

                case 1:
                    selectionSort(arreglo);
                    break;

                case 2:
                    bubbleSort(arreglo);
                    break;

                case 3:
                    insertionSort(arreglo);
                    break;

                case 4:
                    mergeSort(arreglo, 0, arreglo.length - 1);
                    break;

                case 5:
                    quickSort(arreglo, 0, arreglo.length - 1);
                    break;

                case 6:
                    heapSort(arreglo);
                    break;

                case 7:
                    countingSort(arreglo);
                    break;

                case 8:
                    radixSort(arreglo);
                    break;

                case 9:
                    bucketSort(arreglo);
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

            System.out.println("\nArreglo ordenado:");
            imprimir(arreglo);

            System.out.println("\nDesea ordenar con otro metodo? (s/n)");
            continuar = sc.next().charAt(0);

        } while (continuar == 's' || continuar == 'S');

        System.out.println("Programa finalizado.");
    }

    // Imprimir arreglo
    public static void imprimir(int[] arr) {

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            int min = i;

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    // Bubble Sort
    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int l, int r) {

        if (l < r) {

            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {

                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Heap Sort
    public static void heapSort(int[] arr) {

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {

            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    // Counting Sort
    public static void countingSort(int[] arr) {

        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];

        for (int num : arr)
            count[num]++;

        int index = 0;

        for (int i = 0; i < count.length; i++) {

            while (count[i] > 0) {

                arr[index++] = i;
                count[i]--;
            }
        }
    }

    // Radix Sort
    public static void radixSort(int[] arr) {

        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSortRadix(arr, exp);
    }

    public static void countSortRadix(int[] arr, int exp) {

        int n = arr.length;

        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {

            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // Bucket Sort
    public static void bucketSort(int[] arr) {

        int max = Arrays.stream(arr).max().getAsInt();

        int bucketCount = arr.length;

        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++)
            buckets.add(new ArrayList<>());

        for (int num : arr) {

            int index = (num * bucketCount) / (max + 1);
            buckets.get(index).add(num);
        }

        int k = 0;

        for (List<Integer> bucket : buckets) {

            Collections.sort(bucket);

            for (int num : bucket)
                arr[k++] = num;
        }
    }
}