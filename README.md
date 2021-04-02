#  Assessment Application 


## Requirements
- JavaSE-11
- Java Swing


## Installation

Application requires JavaSE-11


```sh
git clone https://github.com/sfkara/Assessment.git

```
## Run

```sh
java -jar Assessment.jar

```
Nasıl kullanılır?
1. Kullanıcı ve bilgisayar birbirinden farklı rakamlara sahip 4 basamaklı bir sayı tutarlar.(0’la başlama,
birbirini tekrar eden sayılar , geçersiz formatta ipucu verme gibi durumların hata yönetimleri
yapılmıştır ).
2. Kullanıcı ilk tahminini Guess a number alanına input olarak girer, Guess! butonuna basar, karşılığında doğru
basamakta olan doğru sayılar için için +1, yanlış basamakta olan doğru rakamlar için -1 değerini ipucu
olarak alır.
3. Kullanıcı Computer’s Guess alanında bilgisayarın yaptığı tahmine göre bilgisayara 2. Adımda bulunan
girdilere göre Clue for Computer alanından uygun olan ipucunu input olarak verir ve Guess! butonuna basar(cİpucu
+x-y formatında olmalıdır.).Bilgisayar aldığı ipucuna göre tahminini güncelleyecektir.
4. Kullanıcı yada bilgisayar doğru tahminde bulunana kadar (+4-0) oyun sürecektir.
5. Test amacıyla cheat code olarak Guess a number alanına “number” yazılarak bilgisayarın tuttuğu
sayıya ulaşılabilir.
