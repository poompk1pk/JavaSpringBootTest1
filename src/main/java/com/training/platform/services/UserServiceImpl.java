package com.training.platform.services;

import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private Jedis redis;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAllByLimit(Integer start, Integer limit, String field) {
        PageRequest page = PageRequest.of(start, limit, Sort.by(Sort.Direction.ASC, field));
        return userRepository.findAll(page);
    }

    @Override
    public List<User> findByCityAndActiveAndAge(String city, Integer active, Integer age) {
        return userRepository.findByCityAndActiveAndAge(city, active, age);
    }

    @Override
    public List<User> findByAgeIn(String ages) {
        List<Integer> numbers = new LinkedList<>();
        for(String s : ages.split(","))
            numbers.add(Integer.parseInt(s));
        return userRepository.findByAgeIn(numbers);
    }
    @Override
    public List<User> findByAgeIn(List<Integer> ages) {
        return userRepository.findByAgeIn(ages);
    }

    @Override
    public List<User> findAllByQuery() {
        return userRepository.findAllByQuery();
    }


    @Override
    public List<User> findAllByParamsQuery(Integer active, String city) {
        return userRepository.findAllByParamsQuery(active, city);
    }

   static Map<String, String> cities =  new HashMap<String, String>();

    {
        cities.put("Bangkok",  "Bangkok");
        cities.put("Krabi Province",  "Krabi Province");
        cities.put("Kanchanaburi Province",  "Kanchanaburi Province");
        cities.put("Kalasin Province",  "Kalasin Province");
        cities.put("Kamphaeng Phet Province",  "Kamphaeng Phet Province");
        cities.put("Khon Kaen Province",  "Khon Kaen Province");
        cities.put("Chanthaburi Province",  "Chanthaburi Province");
        cities.put("Chachoengsao Province",  "Chachoengsao Province");
        cities.put("Chonburi Province",  "Chonburi Province");
        cities.put("Chainat Province",  "Chainat Province");
        cities.put("Chaiyaphum Province",  "Chaiyaphum Province");
        cities.put("Chumphon Province",  "Chumphon Province");
        cities.put("Chiang Rai Province",  "Chiang Rai Province");
        cities.put("Chiang Mai Province",  "Chiang Mai Province");
        cities.put("Trang Province",  "Trang Province");
        cities.put("Trat Province",  "Trat Province");
        cities.put("Tak Province",  "Tak Province");
        cities.put("Nakhon Nayok Province",  "Nakhon Nayok Province");
        cities.put("Nakhon Pathom Province",  "Nakhon Pathom Province");
        cities.put("Nakhon Phanom Province",  "Nakhon Phanom Province");
        cities.put("Nakhon Ratchasima Province",  "Nakhon Ratchasima Province");
        cities.put("Nakhon Si Thammarat Province",  "Nakhon Si Thammarat Province");
        cities.put("Nakhon Sawan Province",  "Nakhon Sawan Province");
        cities.put("Nonthaburi Province",  "Nonthaburi Province");
        cities.put("Narathiwat Province",  "Narathiwat Province");
        cities.put("Nan Province",  "Nan Province");
        cities.put("Bueng Kan Province",  "Bueng Kan Province");
        cities.put("Buriram Province",  "Buriram Province");
        cities.put("Pathum Thani Province",  "Pathum Thani Province");
        cities.put("Prachuap Khiri Khan Province",  "Prachuap Khiri Khan Province");
        cities.put("Prachinburi Province",  "Prachinburi Province");
        cities.put("Pattani Province",  "Pattani Province");
        cities.put("Phra Nakhon Si Ayutthaya Province",  "Phra Nakhon Si Ayutthaya Province");
        cities.put("Phayao Province",  "Phayao Province");
        cities.put("Phang Nga Province",  "Phang Nga Province");
        cities.put("Phatthalung Province",  "Phatthalung Province");
        cities.put("Phichit Province",  "Phichit Province");
        cities.put("Phitsanulok Province",  "Phitsanulok Province");
        cities.put("Phetchaburi Province",  "Phetchaburi Province");
        cities.put("Phetchabun Province",  "Phetchabun Province");
        cities.put("Phrae Province",  "Phrae Province");
        cities.put("Phuket Province",  "Phuket Province");
        cities.put("Maha Sarakham Province",  "Maha Sarakham Province");
        cities.put("Mukdahan Province",  "Mukdahan Province");
        cities.put("Mae Hong Son Province",  "Mae Hong Son Province");
        cities.put("Yasothon Province",  "Yasothon Province");
        cities.put("Yala Province",  "Yala Province");
        cities.put("Roi Et Province",  "Roi Et Province");
        cities.put("Ranong Province",  "Ranong Province");
        cities.put("Rayong Province",  "Rayong Province");
        cities.put("Ratchaburi Province",  "Ratchaburi Province");
        cities.put("Lopburi Province",  "Lopburi Province");
        cities.put("Lampang Province",  "Lampang Province");
        cities.put("Lamphun Province",  "Lamphun Province");
        cities.put("Loei Province",  "Loei Province");
        cities.put("Sisaket Province",  "Sisaket Province");
        cities.put("Sakon Nakhon Province",  "Sakon Nakhon Province");
        cities.put("Songkhla Province",  "Songkhla Province");
        cities.put("Satun Province",  "Satun Province");
        cities.put("Samut Prakan Province",  "Samut Prakan Province");
        cities.put("Samut Songkhram Province",  "Samut Songkhram Province");
        cities.put("Samut Sakhon Province",  "Samut Sakhon Province");
        cities.put("Sa Kaeo Province",  "Sa Kaeo Province");
        cities.put("Saraburi Province",  "Saraburi Province");
        cities.put("Sing Buri Province",  "Sing Buri Province");
        cities.put("Sukhothai Province",  "Sukhothai Province");
        cities.put("Suphan Buri Province",  "Suphan Buri Province");
        cities.put("Surat Thani Province",  "Surat Thani Province");
        cities.put("Surin Province",  "Surin Province");
        cities.put("Nong Khai Province",  "Nong Khai Province");
        cities.put("Nong Bua Lamphu Province",  "Nong Bua Lamphu Province");
        cities.put("Ang Thong Province",  "Ang Thong Province");
        cities.put("Amnat Charoen Province",  "Amnat Charoen Province");
        cities.put("Udon Thani Province",  "Udon Thani Province");
        cities.put("Uttaradit Province",  "Uttaradit Province");
        cities.put("Uthai Thani Province",  "Uthai Thani Province");
        cities.put("Ubon Ratchathani Province",  "Ubon Ratchathani Province");
    }
    @Override
    public Map<String, String> getCities() {

        return cities;
    }



    @Override
    public List<User> findAllByJpqlQuery() {
        return userRepository.findAllByJpqlQuery();
    }

    @Override
    public List<User> findAllByJpqlParamsQuery(Integer active, String city) {
        return userRepository.findAllByJpqlParamsQuery(active, city);
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User save(Map<String,String> inputs) throws Exception {
        try {
            User user = new User();
            user.setName(inputs.get("name"));
            user.setSurname(inputs.get("surname"));
            user.setEmail(inputs.get("email"));

           // user.setPassword(inputs.get("password"));
           // user.setConfirm_password(inputs.get("confirm_password"));

            // Encryted Password
            user.setPassword(utilsService.encrytePassword(inputs.get("password")));
            user.setConfirm_password(utilsService.encrytePassword(inputs.get("confirm_password")));
            user.setAge(Integer.parseInt(inputs.get("age")));
            user.setAddress(inputs.get("address"));
            user.setCity(inputs.get("city"));
            user.setMobile(inputs.get("mobile"));
            user.setActive(1);
            user.setCreatedAt(new Date());

            return userRepository.save(user);
        } catch (Exception ex) {
            throw ex;
        }
    }


    @Override
    public boolean isEmailAlreadyInUse(String email) {
        boolean emailInuse = true;
        if (userRepository.findByEmail(email) == null) {
            emailInuse = false;
        }
        return emailInuse;
    }

    @Override
    public User update(Optional<User> user, Map<String,String> inputs) throws Exception {
        try {
            user.get().setName(inputs.get("name"));
            user.get().setSurname(inputs.get("surname"));
            user.get().setAge(Integer.parseInt(inputs.get("age")));
            user.get().setAddress(inputs.get("address"));
            user.get().setCity(inputs.get("city"));
            user.get().setMobile(inputs.get("mobile"));
            user.get().setUpdatedAt(new Date());
            return userRepository.save(user.get());
        } catch (Exception ex) {
            throw ex;
        }
    }
    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }


}
