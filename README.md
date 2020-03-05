# Movie Catalog - Submission 4

Aplikasi ini dibuat untuk memenuhi syarat kelulusan pada kelas Dicoding [Belajar Fundamental Aplikasi Android](https://www.dicoding.com/academies/14)

### Fitur yang harus ada pada aplikasi

1. Daftar Film
    * Syarat:
		* Terdapat 2 (dua) halaman yang menampilkan daftar film (**Movies** dan **Tv Show**).
		* Menggunakan Fragment untuk menampung halaman **Movies** dan **Tv Show**.
		* Menggunakan RecyclerView untuk menampilkan daftar film dengan jumlah **minimal 10 item**.
		* Menggunakan TabLayout, BottomNavigationView, atau yang lainnya sebagai navigasi antara halaman **Movies** dan **Tv Show**.
		* Menampilkan indikator loading ketika data sedang dimuat.
		
2. Detail Film
    * Syarat: 
		* Menampilkan poster dan informasi film pada halaman detail film.
		* Menggunakan ConstraintLayout untuk menyusun layout.
		* Menampilkan indikator loading ketika data sedang dimuat.
		
3. Favorite Film
	* Syarat:
		* Dapat menyimpan film ke database favorite.
		* Dapat menghapus film dari database favorite.
		* Terdapat halaman untuk menampilkan daftar Favorite Movies.
		* Terdapat halaman untuk menampilkan daftar Favorite Tv Show.
		* Menampilkan halaman detail dari daftar Favorite.

4. Localization
	* Syarat:
		* Aplikasi harus mendukung bahasa Indonesia dan bahasa Inggris.
		
5. Configuration Changes
	* Syarat:
		* Aplikasi harus bisa menjaga data yang sudah dimuat ketika terjadi pergantian orientasi dari potrait ke landscape atau sebaliknya.
		
5. Mempertahankan semua fitur aplikasi dan komponen yang digunakan pada aplikasi Movie Catalogue (UI/UX).

### Additional
    
	* Menggunakan library pihak ketiga seperti Retrofit, Fast Android Networking, dsb.
	* Menggunakan library penyimpanan lokal pihak ketiga seperti Room, Realm, dsb.
    * Menerapkan design pattern seperti MVP, MVVM, Arch Component, dsb.
    * Aplikasi bisa memberikan pesan eror jika data tidak berhasil ditampilkan.
    * Menuliskan kode dengan bersih.

### Resources
	* Untuk mendapatkan API Key silakan ikuti tutorial pada tautan berikut:
		https://blog.dicoding.com/registrasi-testing-themoviedb-api/
	* Gunakan endpoint berikut untuk mendapatkan data Movies:
		https://api.themoviedb.org/3/discover/movie?api_key={API KEY}&language=en-US
	* Gunakan endpoint berikut untuk mendapatkan data Tv Show:
		https://api.themoviedb.org/3/discover/tv?api_key={API KEY}&language=en-US
	* Gunakan url berikut untuk mendapatkan poster film.
		https://image.tmdb.org/t/p/{POSTER SIZE}{POSTER FILENAME}

### Author
_M Hendri Febriansyah_