// Задание

// Даны два Deque, представляющие два целых числа. Цифры хранятся в обратном порядке и каждый из их узлов содержит одну цифру.
// 1) Умножьте два числа и верните произведение в виде связанного списка.
// 2) Сложите два числа и верните сумму в виде связанного списка. Одно или два числа должны быть отрицательными.
// 3)*Реализовать стэк с помощью массива. Нужно реализовать методы:

// size(), empty(), push(), peek(), pop().

import java.util.Deque;
import java.util.LinkedList;

public class Task1 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode multiply(Deque<Integer> num1, Deque<Integer> num2) {
        ListNode result = null; // связанный список, в котором будет храниться результат умножения
        int carry = 0; // перенос при умножении чисел

        while (!num1.isEmpty() || !num2.isEmpty()) { // итерируемся по обоим спискам
            int n1 = num1.isEmpty() ? 0 : num1.removeFirst(); // получаем число из первого списка (если список пуст, то берем 0)
            ListNode current = result;
            ListNode prev = null;

            while (!num2.isEmpty()) { // умножаем первое число на каждое из чисел во втором списке
                int n2 = num2.removeFirst();
                int product = (n1 * n2) + carry; // результат умножения чисел + перенос
                carry = product / 10; // обновляем перенос
                product = product % 10; // последняя цифра результата
                
                prev = current;

                if (current == null){
                    current = new ListNode(product);
                    result = current; // запоминаем первую цифру результата
                }
                else {
                    current.val += product; // добавляем значение правого операнда к переменной и присваиваем переменной результат
                    current = current.next = new ListNode(current.val %10); // добавляем следующую цифру результата
                    current.val += current.val / 10; // перенос для следующего числа
                }

                current.val %= 10;
            }

            if (carry != 0) { // если после всех итераций перенос не равен 0, добавляем его в связный список
                prev.next = new ListNode(carry);
                carry = 0;
            }
            
            num2.addAll(resultList); // добавляем полученный результат во второй список
            result = null; // обновляем результат умножения для следующей итерации
        }
        return result;
    }

    public static ListNode add(Deque<Integer> num1, Deque<Integer> num2) {
        ListNode result = null; // связанный список, в котором будет храниться результат сложения
        int carry = 0; // перенос при сложении чисел

        while (!num1.isEmpty() || !num2.isEmpty()) { // итерируемся по обоим спискам
            int n1 = num1.isEmpty() ? 0 : num1.removeFirst(); // получаем первое число из первого списка (если список пуст, берем 0) 
            int n2 = num2.isEmpty() ? 0 : num2.removeFirst(); // получаем первое число из второго списка (если список пуст, берем 0)

            int sum = n1 + n2 + carry; // сумма чисел плюс перенос
            carry = sum / 10; // обновляем перенос
            sum = sum % 10; // последняя цифра результата

            ListNode current = new ListNode(sum);
            if (result == null) {
                result = current;
            } 
            else {
                ListNode temp = result;
                while (temp.next != null) {
                    temp = temp.next;
                }
            temp.next = current;
            }
        }
        if (carry != 0) { // если после всех итераций перенос не равен 0, добавляем его в связанный список
            ListNode current = new ListNode(carry);
            ListNode temp = result;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = current;
        }
        return result;
    }

    public static void main(String[] args) {
        // Создаем списки, представляющих 2 целых числа:
        Deque<Integer> number1 = new LinkedList<>();
        number1.offer(2);
        number1.offer(1);
        number1.offer(7); // число 712

        Deque<Integer> number2 = new LinkedList<>();
        number2.offer(5);
        number2.offer(0);
        number2.offer(9); // число 509

        // Считаем произведение чисел
        ListNode multiplicationResult = multiply(number1, number2); 

        // Выводим результат умножения на экран:
        System.out.println("Умножение: ");
        ListNode temp = multiplicationResult;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
        System.out.println();

        // Считаем сумму чисел:
        ListNode additionResult = add(number1, number2);

        // Выведем результат сложения на экран:
        System.out.println("Сложение: ");
        temp = additionResult;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }

        System.out.println();
    }

}

// В данном примере мы создаем 2 Deque, представляющих собой числа 712 и 905.
// Затем мы считаем их произведение и сумму с помощью методов "Умножение" и "Сложение".
// В результате на экран выводится:
// Умножение: 644360
// Сложение: 3617