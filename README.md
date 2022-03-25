# Bitirme Projesi

Projenin Konusu:
Bir marketteki ürünlerin satış fiyatlarına göre son fiyatlarını belirleyen servisin Spring Boot Framework
kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması.

> **Gereksinimler:**

> **Backend:**

-  Kullanıcıdan kullanıcı adı, şifre, isim, soy isim bilgilerini alarak sisteme kayıt yapılır.:heavy_check_mark:
- Sisteme kayıtlı kullanıcılar market ürünlerinin veri girişini yapabilir.:heavy_check_mark:
- Ürün türlerine göre KDV oranları değişiklik göstermektedir. Bu oranlar aşağıdaki tabloda
belirtilmiştir. __**Zaman zaman KDV oranları değişiklik gösterebilmektedir.**__

![Image](https://www.linkpicture.com/q/Untitled_395.png)


- Ürün için veri girişi yapacak kullanıcı; ürünün adı, ürünün türü ve vergisiz satış fiyatı alanlarını
doldurur. Her bir ürün için KDV Tutarı ve ürünün son fiyatı da hesaplanarak sisteme kaydedilir.:heavy_check_mark:
> **Kurallar ve gereksinimler:**
- Sisteme yeni kullanıcı tanımlanabilir, güncellenebilir ve silinebilir.:heavy_check_mark:
- Sisteme yeni ürünler tanımlanabilir, güncellenebilir ve silinebilir.:heavy_check_mark:
- Ürünlerin fiyatları güncellenebilir.:heavy_check_mark:
- KDV oranları değiştiğinde sistemdeki ürünlere de bu değişiklik yansıtılmalıdır. Herhangi bir hata
durumunda tüm işlemler geri alınmalıdır.:heavy_check_mark:
- Tüm ürünler listelenebilmelidir.:heavy_check_mark:
- Ürün türlerine göre ürünler listelenebilmelidir.:heavy_check_mark:
- Belirli bir fiyat aralığındaki ürünler listelenebilmelidir.:heavy_check_mark:
- Ürün türlerine göre aşağıdaki gibi detay veri içeren bir bilgilendirme alınabilmelidir.:heavy_check_mark:

![Image](https://www.linkpicture.com/q/22_57.png)

> Validasyonlar:
- Aynı kullanıcı adı ile kullanıcı tanımı yapılamaz.:heavy_check_mark:
- Kullanıcı girişi kullanıcı adı & şifre kombinasyonu ile yapılır.:heavy_check_mark:
- Ürün türü, fiyatı, adı gibi alanlar boş olamaz.:heavy_check_mark:
- Ürün fiyatı sıfır ya da negatif olamaz.:heavy_check_mark:
- KDV oranı negatif olamaz.:heavy_check_mark:
> Authentication:
- Güvenli endpointler kullanınız. (jwt, bearer vs. ):heavy_check_mark:
> Response:
- Başarılı ve başarısız responselar için modeller tanımlayın ve bunları kullanın.:heavy_check_mark:
> Dökümantasyon:
- Open API Specification (Swagger tercih sebebi):heavy_check_mark:
> Exception Handling:
- Hatalı işlemler için doğru hata kodlarının dönüldüğünden emin olunuz.:heavy_check_mark:
> Test:
- Unit ve integration testleri yazınız. :heavy_check_mark:

